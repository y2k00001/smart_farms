<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="农田租赁合同ID" prop="farmlandLesseeId">
        <el-input
          v-model="queryParams.farmlandLesseeId"
          placeholder="请输入农田租赁合同ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="农田id" prop="farmlandId">
        <el-input
          v-model="queryParams.farmlandId"
          placeholder="请输入农田id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租户id" prop="lesseeId">
        <el-input
          v-model="queryParams.lesseeId"
          placeholder="请输入租户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租赁单价;单位：元/天" prop="lesseePrice">
        <el-input
          v-model="queryParams.lesseePrice"
          placeholder="请输入租赁单价;单位：元/天"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租赁总金额;单位：元" prop="lesseeAmount">
        <el-input
          v-model="queryParams.lesseeAmount"
          placeholder="请输入租赁总金额;单位：元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租赁天数;单位:天" prop="lesseeDay">
        <el-input
          v-model="queryParams.lesseeDay"
          placeholder="请输入租赁天数;单位:天"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租赁开始时间" prop="lesseeStartDate">
        <el-date-picker clearable
          v-model="queryParams.lesseeStartDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择租赁开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="租赁结束时间" prop="lesseeEndDate">
        <el-date-picker clearable
          v-model="queryParams.lesseeEndDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择租赁结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="合同附件id集合" prop="lesseeFiles">
        <el-input
          v-model="queryParams.lesseeFiles"
          placeholder="请输入合同附件id集合"
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
      <el-form-item label="是否删除" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入是否删除"
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
          v-hasPermi="['farmlands:lessee:add']"
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
          v-hasPermi="['farmlands:lessee:edit']"
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
          v-hasPermi="['farmlands:lessee:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['farmlands:lessee:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="lesseeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="农田租赁合同ID" align="center" prop="farmlandLesseeId" />
      <el-table-column label="农田id" align="center" prop="farmlandId" />
      <el-table-column label="租户id" align="center" prop="lesseeId" />
      <el-table-column label="租赁单价;单位：元/天" align="center" prop="lesseePrice" />
      <el-table-column label="租赁总金额;单位：元" align="center" prop="lesseeAmount" />
      <el-table-column label="租赁天数;单位:天" align="center" prop="lesseeDay" />
      <el-table-column label="租赁开始时间" align="center" prop="lesseeStartDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lesseeStartDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="租赁结束时间" align="center" prop="lesseeEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lesseeEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="租赁状态;0，待支付，1，已生效，2已结束，3违约" align="center" prop="status" />
      <el-table-column label="合同附件id集合" align="center" prop="lesseeFiles" />
      <el-table-column label="创建人姓名" align="center" prop="createByName" />
      <el-table-column label="是否删除" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['farmlands:lessee:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['farmlands:lessee:remove']"
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

    <!-- 添加或修改农田租赁信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农田租赁合同ID" prop="farmlandLesseeId">
          <el-input v-model="form.farmlandLesseeId" placeholder="请输入农田租赁合同ID" />
        </el-form-item>
        <el-form-item label="农田id" prop="farmlandId">
          <el-input v-model="form.farmlandId" placeholder="请输入农田id" />
        </el-form-item>
        <el-form-item label="租户id" prop="lesseeId">
          <el-input v-model="form.lesseeId" placeholder="请输入租户id" />
        </el-form-item>
        <el-form-item label="租赁单价;单位：元/天" prop="lesseePrice">
          <el-input v-model="form.lesseePrice" placeholder="请输入租赁单价;单位：元/天" />
        </el-form-item>
        <el-form-item label="租赁总金额;单位：元" prop="lesseeAmount">
          <el-input v-model="form.lesseeAmount" placeholder="请输入租赁总金额;单位：元" />
        </el-form-item>
        <el-form-item label="租赁天数;单位:天" prop="lesseeDay">
          <el-input v-model="form.lesseeDay" placeholder="请输入租赁天数;单位:天" />
        </el-form-item>
        <el-form-item label="租赁开始时间" prop="lesseeStartDate">
          <el-date-picker clearable
            v-model="form.lesseeStartDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择租赁开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="租赁结束时间" prop="lesseeEndDate">
          <el-date-picker clearable
            v-model="form.lesseeEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择租赁结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同附件id集合" prop="lesseeFiles">
          <el-input v-model="form.lesseeFiles" placeholder="请输入合同附件id集合" />
        </el-form-item>
        <el-form-item label="创建人姓名" prop="createByName">
          <el-input v-model="form.createByName" placeholder="请输入创建人姓名" />
        </el-form-item>
        <el-form-item label="是否删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入是否删除" />
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
import { listLessee, getLessee, delLessee, addLessee, updateLessee } from "@/api/farmlands/lessee";

export default {
  name: "Lessee",
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
      // 农田租赁信息表格数据
      lesseeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        farmlandLesseeId: null,
        farmlandId: null,
        lesseeId: null,
        lesseePrice: null,
        lesseeAmount: null,
        lesseeDay: null,
        lesseeStartDate: null,
        lesseeEndDate: null,
        status: null,
        lesseeFiles: null,
        createByName: null,
        isDeleted: null
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
    /** 查询农田租赁信息列表 */
    getList() {
      this.loading = true;
      listLessee(this.queryParams).then(response => {
        this.lesseeList = response.rows;
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
        farmlandLesseeId: null,
        farmlandId: null,
        lesseeId: null,
        lesseePrice: null,
        lesseeAmount: null,
        lesseeDay: null,
        lesseeStartDate: null,
        lesseeEndDate: null,
        status: null,
        lesseeFiles: null,
        createByName: null,
        createBy: null,
        createTime: null,
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
      this.title = "添加农田租赁信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLessee(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农田租赁信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLessee(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLessee(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除农田租赁信息编号为"' + ids + '"的数据项？').then(function() {
        return delLessee(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('farmlands/lessee/export', {
        ...this.queryParams
      }, `lessee_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
