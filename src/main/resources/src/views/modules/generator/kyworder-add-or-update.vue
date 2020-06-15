<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="服务费率" prop="tkservicerate">
      <el-input v-model="dataForm.tkservicerate" placeholder="服务费率"></el-input>
    </el-form-item>
    <el-form-item label="商品单价" prop="payprice">
      <el-input v-model="dataForm.payprice" placeholder="商品单价"></el-input>
    </el-form-item>
    <el-form-item label="商品链接" prop="auctionurl">
      <el-input v-model="dataForm.auctionurl" placeholder="商品链接"></el-input>
    </el-form-item>
    <el-form-item label="商品id" prop="auctionid">
      <el-input v-model="dataForm.auctionid" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="" prop="tksharerate">
      <el-input v-model="dataForm.tksharerate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="预估付款服务费" prop="preservicefee">
      <el-input v-model="dataForm.preservicefee" placeholder="预估付款服务费"></el-input>
    </el-form-item>
    <el-form-item label="" prop="presell">
      <el-input v-model="dataForm.presell" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="付款金额" prop="totalalipayfeestring">
      <el-input v-model="dataForm.totalalipayfeestring" placeholder="付款金额"></el-input>
    </el-form-item>
    <el-form-item label="订单创建时间" prop="createtime">
      <el-input v-model="dataForm.createtime" placeholder="订单创建时间"></el-input>
    </el-form-item>
    <el-form-item label="订单状态" prop="paystatus">
      <el-input v-model="dataForm.paystatus" placeholder="订单状态"></el-input>
    </el-form-item>
    <el-form-item label="商品名称" prop="auctiontitle">
      <el-input v-model="dataForm.auctiontitle" placeholder="商品名称"></el-input>
    </el-form-item>
    <el-form-item label="所属店铺" prop="exshoptitle">
      <el-input v-model="dataForm.exshoptitle" placeholder="所属店铺"></el-input>
    </el-form-item>
    <el-form-item label="结算金额" prop="realpayfeestring">
      <el-input v-model="dataForm.realpayfeestring" placeholder="结算金额"></el-input>
    </el-form-item>
    <el-form-item label="" prop="relationid">
      <el-input v-model="dataForm.relationid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="specialid">
      <el-input v-model="dataForm.specialid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="inviteraccountname">
      <el-input v-model="dataForm.inviteraccountname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="sharerate">
      <el-input v-model="dataForm.sharerate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="relationapp">
      <el-input v-model="dataForm.relationapp" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tkalimmsharerate">
      <el-input v-model="dataForm.tkalimmsharerate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="inviterid">
      <el-input v-model="dataForm.inviterid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="earningtime">
      <el-input v-model="dataForm.earningtime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tkbiztag">
      <el-input v-model="dataForm.tkbiztag" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tk3rdpubsharefee">
      <el-input v-model="dataForm.tk3rdpubsharefee" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tk3rdtypestr">
      <el-input v-model="dataForm.tk3rdtypestr" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="exmemberid">
      <el-input v-model="dataForm.exmemberid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="realpayfee">
      <el-input v-model="dataForm.realpayfee" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="terminaltype">
      <el-input v-model="dataForm.terminaltype" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="auctionnum">
      <el-input v-model="dataForm.auctionnum" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="taobaotradeparentid">
      <el-input v-model="dataForm.taobaotradeparentid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="exnickname">
      <el-input v-model="dataForm.exnickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tkshareratetostring">
      <el-input v-model="dataForm.tkshareratetostring" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="tkpubsharefeestring">
      <el-input v-model="dataForm.tkpubsharefeestring" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="feestring">
      <el-input v-model="dataForm.feestring" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="discountandsubsidytostring">
      <el-input v-model="dataForm.discountandsubsidytostring" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="finaldiscounttostring">
      <el-input v-model="dataForm.finaldiscounttostring" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="prefinishservicefee">
      <el-input v-model="dataForm.prefinishservicefee" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="biztype">
      <el-input v-model="dataForm.biztype" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="插表时间" prop="insertTime">
      <el-input v-model="dataForm.insertTime" placeholder="插表时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          tkservicerate: '',
          payprice: '',
          auctionurl: '',
          auctionid: '',
          tksharerate: '',
          preservicefee: '',
          presell: '',
          totalalipayfeestring: '',
          createtime: '',
          paystatus: '',
          auctiontitle: '',
          exshoptitle: '',
          realpayfeestring: '',
          relationid: '',
          specialid: '',
          inviteraccountname: '',
          sharerate: '',
          relationapp: '',
          tkalimmsharerate: '',
          inviterid: '',
          earningtime: '',
          tkbiztag: '',
          tk3rdpubsharefee: '',
          tk3rdtypestr: '',
          exmemberid: '',
          realpayfee: '',
          terminaltype: '',
          auctionnum: '',
          taobaotradeparentid: '',
          exnickname: '',
          tkshareratetostring: '',
          tkpubsharefeestring: '',
          feestring: '',
          discountandsubsidytostring: '',
          finaldiscounttostring: '',
          prefinishservicefee: '',
          biztype: '',
          insertTime: ''
        },
        dataRule: {
          tkservicerate: [
            { required: true, message: '服务费率不能为空', trigger: 'blur' }
          ],
          payprice: [
            { required: true, message: '商品单价不能为空', trigger: 'blur' }
          ],
          auctionurl: [
            { required: true, message: '商品链接不能为空', trigger: 'blur' }
          ],
          auctionid: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          tksharerate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          preservicefee: [
            { required: true, message: '预估付款服务费不能为空', trigger: 'blur' }
          ],
          presell: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalalipayfeestring: [
            { required: true, message: '付款金额不能为空', trigger: 'blur' }
          ],
          createtime: [
            { required: true, message: '订单创建时间不能为空', trigger: 'blur' }
          ],
          paystatus: [
            { required: true, message: '订单状态不能为空', trigger: 'blur' }
          ],
          auctiontitle: [
            { required: true, message: '商品名称不能为空', trigger: 'blur' }
          ],
          exshoptitle: [
            { required: true, message: '所属店铺不能为空', trigger: 'blur' }
          ],
          realpayfeestring: [
            { required: true, message: '结算金额不能为空', trigger: 'blur' }
          ],
          relationid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          specialid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          inviteraccountname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sharerate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          relationapp: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tkalimmsharerate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          inviterid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          earningtime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tkbiztag: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tk3rdpubsharefee: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tk3rdtypestr: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          exmemberid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          realpayfee: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          terminaltype: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          auctionnum: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          taobaotradeparentid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          exnickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tkshareratetostring: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          tkpubsharefeestring: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          feestring: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          discountandsubsidytostring: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          finaldiscounttostring: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          prefinishservicefee: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          biztype: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          insertTime: [
            { required: true, message: '插表时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/generator/kyworder/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.tkservicerate = data.kYwOrder.tkservicerate
                this.dataForm.payprice = data.kYwOrder.payprice
                this.dataForm.auctionurl = data.kYwOrder.auctionurl
                this.dataForm.auctionid = data.kYwOrder.auctionid
                this.dataForm.tksharerate = data.kYwOrder.tksharerate
                this.dataForm.preservicefee = data.kYwOrder.preservicefee
                this.dataForm.presell = data.kYwOrder.presell
                this.dataForm.totalalipayfeestring = data.kYwOrder.totalalipayfeestring
                this.dataForm.createtime = data.kYwOrder.createtime
                this.dataForm.paystatus = data.kYwOrder.paystatus
                this.dataForm.auctiontitle = data.kYwOrder.auctiontitle
                this.dataForm.exshoptitle = data.kYwOrder.exshoptitle
                this.dataForm.realpayfeestring = data.kYwOrder.realpayfeestring
                this.dataForm.relationid = data.kYwOrder.relationid
                this.dataForm.specialid = data.kYwOrder.specialid
                this.dataForm.inviteraccountname = data.kYwOrder.inviteraccountname
                this.dataForm.sharerate = data.kYwOrder.sharerate
                this.dataForm.relationapp = data.kYwOrder.relationapp
                this.dataForm.tkalimmsharerate = data.kYwOrder.tkalimmsharerate
                this.dataForm.inviterid = data.kYwOrder.inviterid
                this.dataForm.earningtime = data.kYwOrder.earningtime
                this.dataForm.tkbiztag = data.kYwOrder.tkbiztag
                this.dataForm.tk3rdpubsharefee = data.kYwOrder.tk3rdpubsharefee
                this.dataForm.tk3rdtypestr = data.kYwOrder.tk3rdtypestr
                this.dataForm.exmemberid = data.kYwOrder.exmemberid
                this.dataForm.realpayfee = data.kYwOrder.realpayfee
                this.dataForm.terminaltype = data.kYwOrder.terminaltype
                this.dataForm.auctionnum = data.kYwOrder.auctionnum
                this.dataForm.taobaotradeparentid = data.kYwOrder.taobaotradeparentid
                this.dataForm.exnickname = data.kYwOrder.exnickname
                this.dataForm.tkshareratetostring = data.kYwOrder.tkshareratetostring
                this.dataForm.tkpubsharefeestring = data.kYwOrder.tkpubsharefeestring
                this.dataForm.feestring = data.kYwOrder.feestring
                this.dataForm.discountandsubsidytostring = data.kYwOrder.discountandsubsidytostring
                this.dataForm.finaldiscounttostring = data.kYwOrder.finaldiscounttostring
                this.dataForm.prefinishservicefee = data.kYwOrder.prefinishservicefee
                this.dataForm.biztype = data.kYwOrder.biztype
                this.dataForm.insertTime = data.kYwOrder.insertTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/kyworder/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'tkservicerate': this.dataForm.tkservicerate,
                'payprice': this.dataForm.payprice,
                'auctionurl': this.dataForm.auctionurl,
                'auctionid': this.dataForm.auctionid,
                'tksharerate': this.dataForm.tksharerate,
                'preservicefee': this.dataForm.preservicefee,
                'presell': this.dataForm.presell,
                'totalalipayfeestring': this.dataForm.totalalipayfeestring,
                'createtime': this.dataForm.createtime,
                'paystatus': this.dataForm.paystatus,
                'auctiontitle': this.dataForm.auctiontitle,
                'exshoptitle': this.dataForm.exshoptitle,
                'realpayfeestring': this.dataForm.realpayfeestring,
                'relationid': this.dataForm.relationid,
                'specialid': this.dataForm.specialid,
                'inviteraccountname': this.dataForm.inviteraccountname,
                'sharerate': this.dataForm.sharerate,
                'relationapp': this.dataForm.relationapp,
                'tkalimmsharerate': this.dataForm.tkalimmsharerate,
                'inviterid': this.dataForm.inviterid,
                'earningtime': this.dataForm.earningtime,
                'tkbiztag': this.dataForm.tkbiztag,
                'tk3rdpubsharefee': this.dataForm.tk3rdpubsharefee,
                'tk3rdtypestr': this.dataForm.tk3rdtypestr,
                'exmemberid': this.dataForm.exmemberid,
                'realpayfee': this.dataForm.realpayfee,
                'terminaltype': this.dataForm.terminaltype,
                'auctionnum': this.dataForm.auctionnum,
                'taobaotradeparentid': this.dataForm.taobaotradeparentid,
                'exnickname': this.dataForm.exnickname,
                'tkshareratetostring': this.dataForm.tkshareratetostring,
                'tkpubsharefeestring': this.dataForm.tkpubsharefeestring,
                'feestring': this.dataForm.feestring,
                'discountandsubsidytostring': this.dataForm.discountandsubsidytostring,
                'finaldiscounttostring': this.dataForm.finaldiscounttostring,
                'prefinishservicefee': this.dataForm.prefinishservicefee,
                'biztype': this.dataForm.biztype,
                'insertTime': this.dataForm.insertTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
