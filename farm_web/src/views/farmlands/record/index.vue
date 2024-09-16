<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="记录ID" prop="fieldManagementRecordId">
        <el-input
          v-model="queryParams.fieldManagementRecordId"
          placeholder="请输入记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作类型;" prop="operationType">
        <el-select v-model="queryParams.operationType" placeholder="请选择操作类型;" clearable>
          <el-option
            v-for="dict in dict.type.operation_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="操作时间" prop="operationTime">
        <el-date-picker clearable
          v-model="queryParams.operationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择操作时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="操作状态" prop="operationStatus">
        <el-select v-model="queryParams.operationStatus" placeholder="请选择操作状态" clearable>
          <el-option
            v-for="dict in dict.type.operation_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="操作结果" prop="operationResult">
        <el-select v-model="queryParams.operationResult" placeholder="请选择操作结果" clearable>
          <el-option
            v-for="dict in dict.type.operation_result"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="操作方式" prop="operationMode">
        <el-input
          v-model="queryParams.operationMode"
          placeholder="请输入操作方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createByName">
        <el-input
          v-model="queryParams.createByName"
          placeholder="请输入创建者姓名"
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
          v-hasPermi="['farmlands:record:add']"
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
          v-hasPermi="['farmlands:record:edit']"
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
          v-hasPermi="['farmlands:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['farmlands:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="记录ID" align="center" prop="fieldManagementRecordId" />
      <el-table-column label="生长周期ID" align="center" prop="growthId" />
      <el-table-column label="操作类型" align="center" prop="operationType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.operation_type" :value="scope.row.operationType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" align="center" prop="operationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作状态" align="center" prop="operationStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.operation_status" :value="scope.row.operationStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作结果" align="center" prop="operationResult">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.operation_result" :value="scope.row.operationResult"/>
        </template>
      </el-table-column>
      <el-table-column label="操作方式;" align="center" prop="operationMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.operation_mode" :value="scope.row.operationMode"/>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createByName" />
      <el-table-column label="是否删除" align="center" prop="isDeleted" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.common_is_del" :value="scope.row.isDeleted"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['farmlands:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['farmlands:record:remove']"
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

    <!-- 添加或修改田间管理记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属生长周期" prop="growthId">
          <el-select v-model="form.growthId">
            <el-option
              v-for="item in growthList"
              :key="item.growthId"
              :label="`${item.farmlandName} - ${item.farmlandLesseeId}`"
              :value="item.growthId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作类型" prop="operationType">
          <el-select v-model="form.operationType" placeholder="请选择操作类型">
            <el-option
              v-for="dict in dict.type.operation_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作时间" prop="operationTime">
          <el-date-picker clearable
            v-model="form.operationTime"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择操作时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作状态" prop="operationStatus">
          <el-select v-model="form.operationStatus" placeholder="请选择操作状态">
            <el-option
              v-for="dict in dict.type.operation_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作结果" prop="operationResult">
          <el-select v-model="form.operationResult" placeholder="请选择操作结果">
            <el-option
              v-for="dict in dict.type.operation_result"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作方式" prop="operationMode">

          <el-select v-model="form.operationMode" placeholder="请选择操作方式">
            <el-option
              v-for="dict in dict.type.operation_mode"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/farmlands/record";
import { listGrowth } from '@/api/farmlands/growth'

export default {
  name: "Record",
  dicts: ['operation_status', 'operation_type', 'operation_result','operation_mode','common_is_del'],
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
      // 田间管理记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fieldManagementRecordId: null,
        growthId: null,
        operationType: null,
        operationTime: null,
        operationStatus: null,
        operationResult: null,
        operationMode: null,
        createByName: null,
      },
      queryGrowthParams: {
        pageNum: 1,
        pageSize: 999,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 农作物生长周期信息表格数据
      growthList: [],
    };
  },
  created() {
    console.log('当前 dicts 的值为:', this.dicts);
    this.getList();
  },
  methods: {
    /** 查询田间管理记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
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
        fieldManagementRecordId: null,
        growthId: null,
        operationType: null,
        operationTime: null,
        operationStatus: null,
        operationResult: null,
        operationMode: null,
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
      this.title = "添加田间管理记录";
      listGrowth(this.queryGrowthParams).then(response => {
        this.growthList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改田间管理记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除田间管理记录编号为"' + ids + '"的数据项？').then(function() {
        return delRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('farmlands/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
