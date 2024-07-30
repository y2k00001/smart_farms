<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生长周期ID" prop="growthId">
        <el-input
          v-model="queryParams.growthId"
          placeholder="请输入生长周期ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="农田租赁合同ID" prop="farmlandLesseeId">
        <el-input
          v-model="queryParams.farmlandLesseeId"
          placeholder="请输入农田租赁合同ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="种子ID" prop="seedId">
        <el-input
          v-model="queryParams.seedId"
          placeholder="请输入种子ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="租赁面积ID" prop="landAreaId">
        <el-input
          v-model="queryParams.landAreaId"
          placeholder="请输入租赁面积ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="农田昵称" prop="farmlandName">
        <el-input
          v-model="queryParams.farmlandName"
          placeholder="请输入农田昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" prop="remarks">
        <el-input
          v-model="queryParams.remarks"
          placeholder="请输入"
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
          v-hasPermi="['farmlands:growth:add']"
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
          v-hasPermi="['farmlands:growth:edit']"
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
          v-hasPermi="['farmlands:growth:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['farmlands:growth:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="growthList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="生长周期ID" align="center" prop="growthId" />
      <el-table-column label="农田ID" align="center" prop="farmlandId" />
      <el-table-column label="农田租赁合同ID" align="center" prop="farmlandLesseeId" />
      <el-table-column label="种子ID" align="center" prop="seedId" />
      <el-table-column label="租赁面积ID" align="center" prop="landAreaId" />
      <el-table-column label="农田昵称" align="center" prop="farmlandName" />
      <el-table-column label="农作物名称" align="center" prop="cropName" />
      <el-table-column label="学名" align="center" prop="scientificName" />
      <el-table-column label="生长阶段;" align="center" prop="growthStage">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.growth_stage" :value="scope.row.growthStage"/>
        </template>
      </el-table-column>


      <el-table-column label="remarks" align="center" prop="remarks" />
      <el-table-column label="创建人姓名" align="center" prop="createByName" />
      <el-table-column label="是否删除" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['farmlands:growth:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['farmlands:growth:remove']"
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

    <!-- 添加或修改农作物生长周期信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农田昵称" prop="farmlandName">
          <el-input v-model="form.farmlandName" placeholder="请输入农田昵称" />
        </el-form-item>
        <el-form-item label="农作物名称" prop="cropName">
          <el-input v-model="form.cropName" placeholder="请输入农作物名称" />
        </el-form-item>
        <el-form-item label="学名" prop="scientificName">
          <el-input v-model="form.scientificName" placeholder="请输入学名" />
        </el-form-item>
        <el-form-item label="生长阶段;" prop="growthStage">
          <el-input v-model="form.growthStage" placeholder="请输入生长阶段;" />
        </el-form-item>
        <el-form-item label="生长时间;" prop="growthTime">
          <el-input v-model="form.growthTime" placeholder="请输入生长时间;" />
        </el-form-item>
        <el-form-item label="生长阶段的起始日期" prop="startDate">
          <el-input v-model="form.startDate" placeholder="请输入生长阶段的起始日期" />
        </el-form-item>
        <el-form-item label="生长阶段的结束日期" prop="endDate">
          <el-input v-model="form.endDate" placeholder="请输入生长阶段的结束日期" />
        </el-form-item>
        <el-form-item label="生长条件;" prop="growthConditions">
          <el-input v-model="form.growthConditions" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="施肥要求;" prop="fertilizerRequirements">
          <el-input v-model="form.fertilizerRequirements" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="灌溉需求;" prop="irrigationNeeds">
          <el-input v-model="form.irrigationNeeds" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="病虫害防治措施;" prop="pestControlMeasures">
          <el-input v-model="form.pestControlMeasures" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="气候适应性;" prop="climateSuitability">
          <el-input v-model="form.climateSuitability" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="生长阶段详细信息;" prop="growthPhaseDetails">
          <el-input v-model="form.growthPhaseDetails" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="预期产量;" prop="yieldExpectation">
          <el-input v-model="form.yieldExpectation" placeholder="请输入预期产量;" />
        </el-form-item>
        <el-form-item label="品种描述;" prop="varietyDescription">
          <el-input v-model="form.varietyDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入" />
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
import { listGrowth, getGrowth, delGrowth, addGrowth, updateGrowth } from "@/api/farmlands/growth";

export default {
  name: "Growth",
  dicts: ['growth_stage'],
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
      // 农作物生长周期信息表格数据
      growthList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        growthId: null,
        farmlandId: null,
        farmlandLesseeId: null,
        seedId: null,
        landAreaId: null,
        farmlandName: null,
        remarks: null,
        createByName: null,
        createTime: null,
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
    /** 查询农作物生长周期信息列表 */
    getList() {
      this.loading = true;
      listGrowth(this.queryParams).then(response => {
        this.growthList = response.rows;
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
        growthId: null,
        farmlandId: null,
        farmlandLesseeId: null,
        seedId: null,
        landAreaId: null,
        farmlandSnap: null,
        seedSnap: null,
        farmlandName: null,
        cropName: null,
        scientificName: null,
        growthStage: null,
        growthTime: null,
        startDate: null,
        endDate: null,
        growthConditions: null,
        fertilizerRequirements: null,
        irrigationNeeds: null,
        pestControlMeasures: null,
        climateSuitability: null,
        soilType: null,
        growthPhaseDetails: null,
        yieldExpectation: null,
        varietyDescription: null,
        remarks: null,
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
      this.title = "添加农作物生长周期信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGrowth(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农作物生长周期信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGrowth(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGrowth(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除农作物生长周期信息编号为"' + ids + '"的数据项？').then(function() {
        return delGrowth(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('farmlands/growth/export', {
        ...this.queryParams
      }, `growth_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
