<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('generator:kyworder:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('generator:kyworder:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="tkservicerate"
        header-align="center"
        align="center"
        label="服务费率">
      </el-table-column>
      <el-table-column
        prop="payprice"
        header-align="center"
        align="center"
        label="商品单价">
      </el-table-column>
      <el-table-column
        prop="auctionurl"
        header-align="center"
        align="center"
        label="商品链接">
      </el-table-column>
      <el-table-column
        prop="auctionid"
        header-align="center"
        align="center"
        label="商品id">
      </el-table-column>
      <el-table-column
        prop="tksharerate"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="preservicefee"
        header-align="center"
        align="center"
        label="预估付款服务费">
      </el-table-column>
      <el-table-column
        prop="presell"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="totalalipayfeestring"
        header-align="center"
        align="center"
        label="付款金额">
      </el-table-column>
      <el-table-column
        prop="createtime"
        header-align="center"
        align="center"
        label="订单创建时间">
      </el-table-column>
      <el-table-column
        prop="paystatus"
        header-align="center"
        align="center"
        label="订单状态">
      </el-table-column>
      <el-table-column
        prop="auctiontitle"
        header-align="center"
        align="center"
        label="商品名称">
      </el-table-column>
      <el-table-column
        prop="exshoptitle"
        header-align="center"
        align="center"
        label="所属店铺">
      </el-table-column>
      <el-table-column
        prop="realpayfeestring"
        header-align="center"
        align="center"
        label="结算金额">
      </el-table-column>
      <el-table-column
        prop="relationid"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="specialid"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="inviteraccountname"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="sharerate"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="relationapp"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="tkalimmsharerate"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="inviterid"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="earningtime"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="tkbiztag"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="tk3rdpubsharefee"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="tk3rdtypestr"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="exmemberid"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="realpayfee"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="terminaltype"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="auctionnum"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="taobaotradeparentid"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="exnickname"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="tkshareratetostring"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="tkpubsharefeestring"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="feestring"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="discountandsubsidytostring"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="finaldiscounttostring"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="prefinishservicefee"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="biztype"
        header-align="center"
        align="center"
        label="">
      </el-table-column>
      <el-table-column
        prop="insertTime"
        header-align="center"
        align="center"
        label="插表时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './kyworder-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/generator/kyworder/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/generator/kyworder/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
