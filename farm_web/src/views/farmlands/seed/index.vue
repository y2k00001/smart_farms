<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="种子ID" prop="seedId">
        <el-input
          v-model="queryParams.seedId"
          placeholder="请输入种子ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="种子名称" prop="seedName">
        <el-input
          v-model="queryParams.seedName"
          placeholder="请输入种子名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="种子产地" prop="origin">
        <el-input
          v-model="queryParams.origin"
          placeholder="请输入种子产地"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生长周期;单位天" prop="growthCycle">
        <el-input
          v-model="queryParams.growthCycle"
          placeholder="请输入生长周期;单位天"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产量;单位吨/亩" prop="yield">
        <el-input
          v-model="queryParams.yield"
          placeholder="请输入产量;单位吨/亩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          v-model="queryParams.remarks"
          placeholder="请输入备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单价;单位元/斤" prop="unitPrice">
        <el-input
          v-model="queryParams.unitPrice"
          placeholder="请输入单价;单位元/斤"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库存数量" prop="stockTotal">
        <el-input
          v-model="queryParams.stockTotal"
          placeholder="请输入库存数量"
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
          v-hasPermi="['farmlands:seed:add']"
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
          v-hasPermi="['farmlands:seed:edit']"
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
          v-hasPermi="['farmlands:seed:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['farmlands:seed:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="seedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="种子ID" align="center" prop="seedId" />
      <el-table-column label="种子名称" align="center" prop="seedName" />
      <el-table-column label="种子类型" align="center" prop="seedType" />
      <el-table-column label="种子产地" align="center" prop="origin" />
      <el-table-column label="生长周期;单位天" align="center" prop="growthCycle" />
      <el-table-column label="产量;单位吨/亩" align="center" prop="yield" />
      <el-table-column label="适宜气候描述" align="center" prop="climate" />
      <el-table-column label="种植技术描述" align="center" prop="plantingTechnique" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="单价;单位元/斤" align="center" prop="unitPrice" />
      <el-table-column label="库存数量" align="center" prop="stockTotal" />
      <el-table-column label="商品状态;0待上架，10在售，20已下架" align="center" prop="status" />
      <el-table-column label="创建人姓名" align="center" prop="createByName" />
      <el-table-column label="是否删除" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['farmlands:seed:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['farmlands:seed:remove']"
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

    <!-- 添加或修改种子信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="种子ID" prop="seedId">
          <el-input v-model="form.seedId" placeholder="请输入种子ID" />
        </el-form-item>
        <el-form-item label="种子名称" prop="seedName">
          <el-input v-model="form.seedName" placeholder="请输入种子名称" />
        </el-form-item>
        <el-form-item label="种子产地" prop="origin">
          <el-input v-model="form.origin" placeholder="请输入种子产地" />
        </el-form-item>
        <el-form-item label="生长周期;单位天" prop="growthCycle">
          <el-input v-model="form.growthCycle" placeholder="请输入生长周期;单位天" />
        </el-form-item>
        <el-form-item label="产量;单位吨/亩" prop="yield">
          <el-input v-model="form.yield" placeholder="请输入产量;单位吨/亩" />
        </el-form-item>
        <el-form-item label="适宜气候描述" prop="climate">
          <el-input v-model="form.climate" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="种植技术描述" prop="plantingTechnique">
          <el-input v-model="form.plantingTechnique" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="单价;单位元/斤" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价;单位元/斤" />
        </el-form-item>
        <el-form-item label="库存数量" prop="stockTotal">
          <el-input v-model="form.stockTotal" placeholder="请输入库存数量" />
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
import { listSeed, getSeed, delSeed, addSeed, updateSeed } from "@/api/farmlands/seed";

export default {
  name: "Seed",
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
      // 种子信息表格数据
      seedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        seedId: null,
        seedName: null,
        seedType: null,
        origin: null,
        growthCycle: null,
        yield: null,
        climate: null,
        plantingTechnique: null,
        remarks: null,
        unitPrice: null,
        stockTotal: null,
        status: null,
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
    /** 查询种子信息列表 */
    getList() {
      this.loading = true;
      listSeed(this.queryParams).then(response => {
        this.seedList = response.rows;
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
        seedId: null,
        seedName: null,
        seedType: null,
        origin: null,
        growthCycle: null,
        yield: null,
        climate: null,
        plantingTechnique: null,
        remarks: null,
        unitPrice: null,
        stockTotal: null,
        status: null,
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
      this.title = "添加种子信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSeed(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改种子信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSeed(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSeed(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除种子信息编号为"' + ids + '"的数据项？').then(function() {
        return delSeed(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('farmlands/seed/export', {
        ...this.queryParams
      }, `seed_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
