<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="配置名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="配置名称"></el-input>
    </el-form-item>
    <el-form-item label="配置说明" prop="title">
      <el-input v-model="dataForm.title" placeholder="配置说明"></el-input>
    </el-form-item>
    <el-form-item label="配置分组" prop="group">
      <el-input v-model="dataForm.group" placeholder="配置分组"></el-input>
    </el-form-item>
    <el-form-item label="配置说明" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="配置说明"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="配置值" prop="value">
      <el-input v-model="dataForm.value" placeholder="配置值"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
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
          name: '',
          title: '',
          group: '',
          remark: '',
          status: '',
          value: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          name: [
            { required: true, message: '配置名称不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '配置说明不能为空', trigger: 'blur' }
          ],
          group: [
            { required: true, message: '配置分组不能为空', trigger: 'blur' }
          ],
          remark: [
            { required: true, message: '配置说明不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          value: [
            { required: true, message: '配置值不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/generator/kconfig/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.kConfig.name
                this.dataForm.title = data.kConfig.title
                this.dataForm.group = data.kConfig.group
                this.dataForm.remark = data.kConfig.remark
                this.dataForm.status = data.kConfig.status
                this.dataForm.value = data.kConfig.value
                this.dataForm.createTime = data.kConfig.createTime
                this.dataForm.updateTime = data.kConfig.updateTime
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
              url: this.$http.adornUrl(`/generator/kconfig/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'title': this.dataForm.title,
                'group': this.dataForm.group,
                'remark': this.dataForm.remark,
                'status': this.dataForm.status,
                'value': this.dataForm.value,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
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
