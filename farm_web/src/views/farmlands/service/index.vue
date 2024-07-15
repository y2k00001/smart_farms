<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务编号" prop="serviceId">
        <el-input
          v-model="queryParams.serviceId"
          placeholder="请输入服务编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务名称" prop="serviceName">
        <el-input
          v-model="queryParams.serviceName"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格" prop="servicePrice">
        <el-input
          v-model="queryParams.servicePrice"
          placeholder="请输入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="serviceType">
        <el-select v-model="queryParams.serviceType" placeholder="请选择类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务" clearable>
          <el-option
            v-for="dict in dict.type.service_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="说明" prop="summary">
        <el-input
          v-model="queryParams.summary"
          placeholder="请输入说明"
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
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
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
          v-hasPermi="['farmlands:service:add']"
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
          v-hasPermi="['farmlands:service:edit']"
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
          v-hasPermi="['farmlands:service:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['farmlands:service:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="serviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务编号" align="center" prop="serviceId" />
      <el-table-column label="服务名称" align="center" prop="serviceName" />
      <el-table-column label="价格" align="center" prop="servicePrice" />
      <el-table-column label="类型" align="center" prop="serviceType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.service_type" :value="scope.row.serviceType"/>
        </template>
      </el-table-column>
      <el-table-column label="说明" align="center" prop="summary" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建人姓名" align="center" prop="createByName" />
      <el-table-column label="创建人" align="center" prop="createBy" />
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
            v-hasPermi="['farmlands:service:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['farmlands:service:remove']"
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

    <!-- 添加或修改服务信息对话框 -->
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

        <el-form-item label="服务名称" prop="serviceName">
          <el-input v-model="form.serviceName" placeholder="请输入服务名称" />
        </el-form-item>
        <el-form-item label="价格" prop="servicePrice">
          <el-input v-model="form.servicePrice" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="类型" prop="serviceType">
          <el-select v-model="form.serviceType" placeholder="请选择类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务">
            <el-option
              v-for="dict in dict.type.service_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="说明" prop="summary">
          <el-input v-model="form.summary" placeholder="请输入说明" />
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
import { listService, getService, delService, addService, updateService } from "@/api/farmlands/service";
import { listFarmland } from '@/api/farmlands/farmland'

export default {
  name: "Service",
  dicts: ['service_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      //农田列表
      farmlandList:[],
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
      // 服务信息表格数据
      serviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serviceId: null,
        serviceName: null,
        servicePrice: null,
        serviceType: null,
        summary: null,
        createByName: null,
        createBy: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        serviceName: [
          { required: true, message: "服务名称不能为空", trigger: "blur" }
        ],
        servicePrice: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        serviceType: [
          { required: true, message: "类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务不能为空", trigger: "change" }
        ],
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
    /** 查询服务信息列表 */
    getList() {
      this.loading = true;
      listService(this.queryParams).then(response => {
        this.serviceList = response.rows;
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
        serviceId: null,
        serviceName: null,
        servicePrice: null,
        serviceType: null,
        summary: null,
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
      this.title = "添加服务信息";
      listFarmland(this.queryFarmlandParams).then(response => {
        this.farmlandList = response.rows;
        this.loading = false;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getService(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改服务信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateService(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addService(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除服务信息编号为"' + ids + '"的数据项？').then(function() {
        return delService(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('farmlands/service/export', {
        ...this.queryParams
      }, `service_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
