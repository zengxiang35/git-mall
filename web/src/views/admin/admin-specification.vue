<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <!--新闻首页内容
      row-key="record=>record.id" 表格的唯一标识每条记录id
      pagination 分页的配置对象
      loading 图标提示
      -->
      <p>
        <a-button type="primary" @click="add">新增</a-button>
      </p>
      <a-table
          :dataSource="specificationList"
          :columns="columns"
          :loading="loading"
          :row-key="record=>record.id"
          :pagination="pagination"
          @change="handleTableChange">
        <template v-slot:action="{text,record}">
          <a-button type="primary" @click="edit(record)">编辑</a-button>
          <a-popconfirm
              title="是否要删除所选项"
              ok-text="是"
              cancel-text="否"
              @confirm="del(record.id)"
          >
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal v-model:visible="modelVisible"
           title="规格编辑表单"
           @ok="handleModalOk"
           ok-text="确定"
           cancel-text="取消">
    <a-form
        :model="specification"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
    >
      <a-form-item label="规格名字">
        <a-input v-model:value="specEntity.specification.specName"/>
      </a-form-item>
      <a-form-item label="规格选项">
        <a-button type="primary" @click="addTableRow">新增规格选项</a-button>
        <table id="tb01" width="340px">
          <thead>
          <tr>
            <td>规格选项</td>
            <td>排序</td>
            <td>操作</td>
          </tr>
          </thead>
          <tbody>
          <tr v-for="pojo in specEntity.specificationOptionList" v-bind:key="pojo.id">
            <td>
              <a-input v-model:value="pojo.optionName"/>
            </td>
            <td>
              <a-input v-model:value="pojo.orders"/>
            </td>
            <td><a-button type="danger" @click="deleTableRow($index)">删除</a-button></td>
          </tr>
          </tbody>
        </table>
      </a-form-item>

    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue';
import axios from "axios";
import {message} from "ant-design-vue";

export default defineComponent({
  name: 'AdminSpecification',
  setup(){

    const specificationList = ref();
    const specification = ref();
    const modelVisible = ref(false);
    const specEntity = ref<any>({
      specification:{},
      specificationOptionList:[]
    });
    const columns =[
      {
        title: '编号',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: '名字',
        dataIndex: 'specName',
        key: 'specName',
      },
      {
        title: 'Action',
        key: 'action',
        slots:{customRender:'action'}
      }
    ];

    const pagination =ref({
      current:1,
      pageSize:3,
      total:0
    });

    const loading = ref(false);
    //定义新闻查询函数
    const handleQuery = (params:any)=>{
      loading.value = true;
      axios.get('http://localhost:8899/mall-query/specificationByPage',{
        params:{
          page:params.page,
          size:params.size
        }
      }).then((response)=> {
        loading.value = false;
        specificationList.value=response.data.list;
        //重置分页按钮
        pagination.value.current = params.page;
        pagination.value.total = response.data.total;

      })
    };

    //分页导航触发函数
    const handleTableChange=(pagination:any)=>{
      handleQuery({
        page:pagination.current,
        size:pagination.pageSize
      })
    }

    //添加模式窗口的打开
    const  add = ()=>{
      modelVisible.value=true;
      specEntity.value.specification={};//初始化
      specEntity.value.specificationOptionList=[];//初始化
    }

    //编辑规格
    const edit = (record:any) =>{
      modelVisible.value = true;
      specEntity.value.specificationOptionList = []; //初始化
      specEntity.value.specification = {}; //初始化
      axios.get('http://localhost:8899/mall-query/querySpecificationOption/' + record.id).then((response) => {
        specEntity.value.specificationOptionList = response.data;
        console.log(specEntity.value.specificationOptionList)
        specEntity.value.specification = record;
      })

    }

    const handleModalOk =() =>{
        axios.post("http://localhost:8899/mall-query/add",specEntity.value).then((response)=>{
          if (response.data.code === 200){
            modelVisible.value=false;
            handleQuery({
              //添加后加载数据
              page:pagination.value.current,
              size:pagination.value.pageSize
            });
          }else {
            message.error(response.data.message);
          }
        });
    }

    //添加新行
    const addTableRow = ()=>{
      specEntity.value.specificationOptionList.push({});
    };

//删除行
    const deleTableRow = (index:number) =>{
      specEntity.value.specificationOptionList.splice(index,1);
    };

    //确认删除
    const del=(id:number)=>{
      axios.get('http://localhost:8899/mall-query/delete/'+id).then((response)=>{
        if (response.data.code === 200){
          handleQuery({
            page:pagination.value.current,
            size:pagination.value.pageSize
          });
        }else {
          message.error(response.data.message);
        }
      });
    }

    //生命周期调用函数
    onMounted(()=>{
      handleQuery({
        page:1,
        size:pagination.value.pageSize
      });
    });

    return{
      specificationList,
      specification,
      modelVisible,
      columns,
      loading,
      pagination,
      handleTableChange,
      handleQuery,
      del,
      add,
      addTableRow,
      deleTableRow,
      specEntity,
      edit,
      handleModalOk
    }

  }
});
</script>

<style scoped>

</style>