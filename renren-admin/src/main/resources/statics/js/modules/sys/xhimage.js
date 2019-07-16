$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/xhimage/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '图片类型', name: 'imgType', index: 'img_type', width: 80 },
			 // { label: '图片地址', name: 'imgUrl', index: 'img_url', width: 80 },
            { label: '图片地址', name: 'imgUrl', width: 60, formatter: function(value, options, row){
                    return   '<img src='+value+' style="height:100px;width=100px" />';
                }},
			{ label: '商品名称', name: 'goodsName', index: 'goods_name', width: 80 },
			{ label: '图片排序', name: 'displayOrder', index: 'display_order', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25,
        rownumHeight:100,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        	// var grid = $(this);
        	// var ids = grid.getDataIDs();
        	// for(var i=0;i<ids.length;i++){
            //     grid.setRowData(ids[i],false,{height:80});
            // }

        }
    });
    new AjaxUpload('#upload', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                $("#imgUrl").val(r.url);
                vm.xhImage['imgUrl']=r.url;
            }else{
                alert(r.msg);
            }
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            goodsName: null
        },
		showList: true,
		title: null,
		xhImage: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.xhImage = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.xhImage.id == null ? "sys/xhimage/save" : "sys/xhimage/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.xhImage),
                    success: function(r){
                        if(r.code === 0){
                            layer.msg("操作成功", {icon: 1});
                            vm.reload();
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
                // var formData = new FormData();
                // formData.append("file", $('#fileSelect')[0].files[0]);
                // formData.append("imgType",$("#imgType").val());
                // formData.append("goodsId",$("#goodsId").val());
                // formData.append("displayOrder",$("#displayOrder").val());
                // //发送文件数据
                // $.ajax({
                //     url: baseURL + url,
                //     dataType: 'json',
                //     type: 'POST',
                //     cache: false, //上传文件不需要缓存
                //     data: formData,
                //     processData: false, // 告诉jQuery不要去处理发送的数据
                //     contentType: false, // 告诉jQuery不要去设置Content-Type请求头
                //     success: function (data) {
                //         if (data.code == 0) {
                //             alert('操作成功', function(){
                //                 vm.reload();
                //             });
                //         }else{
                //             alert(data.msg);
                //         }
                //     },
                //     error: function (response) {
                //         console.log(response);
                //     }
                // });

			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "sys/xhimage/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "sys/xhimage/info/"+id, function(r){
                vm.xhImage = r.xhImage;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'goodsName': vm.q.goodsName
                },
                page:page
            }).trigger("reloadGrid");
		}

    }
});