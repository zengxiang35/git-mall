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
          :dataSource="brandList"
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
           title="品牌编辑表单"
           @ok="handleModalOk"
           ok-text="确定"
           cancel-text="取消">
    <a-form
        :model="tbBrand"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
    >
      <a-form-item label="品牌名字">
        <a-input v-model:value="tbBrand.name"/>
      </a-form-item>
      <a-form-item label="首字母">
        <a-input v-model:value="tbBrand.firstChar"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue';
import axios from "axios";
import {message} from "ant-design-vue";

export default defineComponent({
  name: 'AdminBrand',
  setup(){

    const brandList = ref();
    const tbBrand = ref();
    const modelVisible = ref(false);
    const columns =[
      {
        title: '编号',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: '名字',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: '首字母',
        dataIndex: 'firstChar',
        key: 'firstChar',
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
      axios.get('http://localhost:8899/mall-query/listByPage',{
        params:{
          page:params.page,
          size:params.size
        }
      }).then((response)=> {
        loading.value = false;
        brandList.value=response.data.list;
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
      tbBrand.value={}; //初始化表单对象
    }


    //编辑新闻
    const edit = (record:any) =>{
      modelVisible.value=true;
      tbBrand.value = record;

    }

    const handleModalOk =() =>{
      axios.post("http://localhost:8899/mall-query/saveBrand",tbBrand.value).then((response)=>{
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

    //确认删除
    const del=(id:number)=>{
      axios.get('http://localhost:8899/mall-query/deleteBrand/'+id).then((response)=>{
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
      brandList,
      handleQuery,
      tbBrand,
      modelVisible,
      loading,
      columns,
      pagination,
      handleTableChange,
      del,
      add,
      edit,
      handleModalOk
    }
  }
});
</script>

<style scoped>

</style>