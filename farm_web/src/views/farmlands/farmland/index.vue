<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号" prop="farmlandId">
        <el-input
          v-model="queryParams.farmlandId"
          placeholder="请输入编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="农田名称" prop="farmlandName">
        <el-input
          v-model="queryParams.farmlandName"
          placeholder="请输入农田名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="面积单位;平方米" prop="area">
        <el-input
          v-model="queryParams.area"
          placeholder="请输入面积单位;平方米"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维度" prop="lat">
        <el-input
          v-model="queryParams.lat"
          placeholder="请输入维度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经度" prop="lon">
        <el-input
          v-model="queryParams.lon"
          placeholder="请输入经度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租赁价格;单位：元/天" prop="leasePrice">
        <el-input
          v-model="queryParams.leasePrice"
          placeholder="请输入租赁价格;单位：元/天"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactPhone">
        <el-input
          v-model="queryParams.contactPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人姓名" prop="createByName">
        <el-input
          v-model="queryParams.createByName"
          placeholder="请输入创建人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['farmlands:farmland:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['farmlands:farmland:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['farmlands:farmland:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['farmlands:farmland:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="farmlandList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="farmlandId" />
      <el-table-column label="农田名称" align="center" prop="farmlandName" />
      <el-table-column label="面积单位;平方米" align="center" prop="area" />
      <el-table-column label="维度" align="center" prop="lat" />
      <el-table-column label="经度" align="center" prop="lon" />
      <el-table-column label="位置" align="center" prop="location" />
      <el-table-column label="农田状态;0.未上架；10，待租赁，20，已租赁" align="center" prop="status" />
      <el-table-column label="租赁价格;单位：元/天" align="center" prop="leasePrice" />
      <el-table-column label="摘要" align="center" prop="summary" />
      <el-table-column label="联系电话" align="center" prop="contactPhone" />
      <el-table-column label="附件ID集合;逗号分割" align="center" prop="fileIds" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.fileIds" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建人姓名" align="center" prop="createByName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['farmlands:farmland:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['farmlands:farmland:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改农田信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农田名称" prop="farmlandName">
          <el-input v-model="form.farmlandName" placeholder="请输入农田名称" />
        </el-form-item>
        <el-form-item label="面积单位;平方米" prop="area">
          <el-input v-model="form.area" placeholder="请输入面积单位;平方米" />
        </el-form-item>
        <el-form-item label="维度" prop="lat">
          <el-input v-model="form.lat" placeholder="请输入维度" />
        </el-form-item>
        <el-form-item label="经度" prop="lon">
          <el-input v-model="form.lon" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入位置" />
        </el-form-item>
        <el-form-item label="租赁价格;单位：元/天" prop="leasePrice">
          <el-input v-model="form.leasePrice" placeholder="请输入租赁价格;单位：元/天" />
        </el-form-item>
        <el-form-item label="摘要" prop="summary">
          <el-input v-model="form.summary" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="描述">
          <editor v-model="form.description" :min-height="192"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="附件ID集合;逗号分割" prop="fileIds">
          <image-upload v-model="form.fileIds"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFarmland, getFarmland, delFarmland, addFarmland, updateFarmland } from "@/api/farmlands/farmland";

export default {
  name: "Farmland",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 农田信息表格数据
      farmlandList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        farmlandId: null,
        farmlandName: null,
        area: null,
        lat: null,
        lon: null,
        location: null,
        status: null,
        leasePrice: null,
        summary: null,
        description: null,
        contactPhone: null,
        fileIds: null,
        createByName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        isDeleted: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询农田信息列表 */
    getList() {
      this.loading = true;
      listFarmland(this.queryParams).then(response => {
        this.farmlandList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        farmlandId: null,
        farmlandName: null,
        area: null,
        lat: null,
        lon: null,
        location: null,
        status: null,
        leasePrice: null,
        summary: null,
        description: null,
        contactPhone: null,
        fileIds: null,
        remark: null,
        createByName: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        isDeleted: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加农田信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFarmland(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农田信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFarmland(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFarmland(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除农田信息编号为"' + ids + '"的数据项？').then(function() {
        return delFarmland(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('farmlands/farmland/export', {
        ...this.queryParams
      }, `farmland_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
