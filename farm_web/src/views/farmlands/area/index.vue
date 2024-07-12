<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="面积别名" prop="landAreaName">
        <el-input
          v-model="queryParams.landAreaName"
          placeholder="请输入面积别名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="面积;单位：平方米" prop="area">
        <el-input
          v-model="queryParams.area"
          placeholder="请输入面积;单位：平方米"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单价;单位：元/天" prop="unitPrice">
        <el-input
          v-model="queryParams.unitPrice"
          placeholder="请输入单价;单位：元/天"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序序号" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入排序序号"
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
          v-hasPermi="['farmlands:area:add']"
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
          v-hasPermi="['farmlands:area:edit']"
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
          v-hasPermi="['farmlands:area:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['farmlands:area:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="areaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="农田租赁面积编号Id" align="center" prop="landAreaId" />
      <el-table-column label="面积别名" align="center" prop="landAreaName" />
      <el-table-column label="面积;单位：平方米" align="center" prop="area" />
      <el-table-column label="单价;单位：元/天" align="center" prop="unitPrice" />
      <el-table-column label="0，未出租；1，已出租" align="center" prop="lesseeStatus" />
      <el-table-column label="排序序号" align="center" prop="sort" />
      <el-table-column label="描述" align="center" prop="remark" />
      <el-table-column label="创建人姓名" align="center" prop="createByName" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['farmlands:area:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['farmlands:area:remove']"
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

    <!-- 添加或修改农田租赁最小面积对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属农田" prop="farmlandId">
          <el-select v-model="form.farmlandId">
            <el-option
              v-for="item in farmlandList"
              :key="item.farmlandId"
              :label="item.farmlandName"
              :value="item.farmlandId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="面积别名" prop="landAreaName">
          <el-input v-model="form.landAreaName" placeholder="请输入面积别名" />
        </el-form-item>
        <el-form-item label="面积;单位：平方米" prop="area">
          <el-input v-model="form.area" placeholder="请输入面积;单位：平方米" />
        </el-form-item>
        <el-form-item label="单价;单位：元/天" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价;单位：元/天" />
        </el-form-item>
        <el-form-item label="排序序号" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序序号" />
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入描述" />
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
import { listArea, getArea, delArea, addArea, updateArea } from "@/api/farmlands/area";
import { listFarmland } from '@/api/farmlands/farmland'

export default {
  name: "Area",
  data() {
    return {
      //农田列表
      farmlandList:[],
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
      // 农田租赁最小面积表格数据
      areaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        landAreaId: null,
        landAreaName: null,
        area: null,
        unitPrice: null,
        lesseeStatus: null,
        sort: null,
      },
      queryFarmlandParams: {
        pageNum: 1,
        pageSize: 9999,
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
    /** 查询农田租赁最小面积列表 */
    getList() {
      this.loading = true;
      listArea(this.queryParams).then(response => {
        this.areaList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getFarmlandList() {
      listFarmland(this.queryFarmlandParams).then(response => {
        this.farmlandList = response.rows;
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
        landAreaId: null,
        landAreaName: null,
        area: null,
        unitPrice: null,
        lesseeStatus: null,
        sort: null,
        remark: null,
        createByName: null,
        createBy: null,
        createTime: null,
        updateTime: null,
        updateBy: null,
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
      this.title = "添加农田租赁最小面积";
      listFarmland(this.queryFarmlandParams).then(response => {
        this.farmlandList = response.rows;
        this.loading = false;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getArea(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农田租赁最小面积";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateArea(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArea(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除农田租赁最小面积编号为"' + ids + '"的数据项？').then(function() {
        return delArea(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('farmlands/area/export', {
        ...this.queryParams
      }, `area_${new Date().getTime()}.xlsx`)
    }
  },

};
</script>
