$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/xhcoupon/list',
        datatype: "json",
        colModel: [
			{ label: '所属类型', name: 'type', index: 'type', width: 80 },
			{ label: '优惠券名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '图片', name: 'img', index: 'img', width: 80 ,formatter: function(value, options, row){
                    return   '<img src='+value+' style="height:100px;width=100px" />';
                }},
			{ label: '优惠券开始时间', name: 'startTime', index: 'start_time', width: 80 }, 			
			{ label: '优惠券结束时间', name: 'endTime', index: 'end_time', width: 80 }, 			
			{ label: '优惠券金额', name: 'money', index: 'money', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80 , formatter: function(value, options, row){
			    if(value===0){
                    return '<span class="label label-success">未开始</span>';
                }else if(value===1){
                    return '<span class="label label-success">进行中</span>';
                }else{
                    return '<span class="label label-danger">结束</span>';
                }
                }},
			{ label: '优惠券的说明', name: 'remarks', index: 'remarks', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '金额满', name: 'fullMoney', index: 'full_money', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
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
        }
    });
    $("#startTime").datepicker({
        format: 'yyyy-mm-dd',
        language : "zh-CN",
        autoclose:true,
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
    }).on('changeDate',function (ev) {
        vm.xhCoupon['startTime']=$("#startTime").val();
    });
    $("#endTime").datepicker({
        format: 'yyyy-mm-dd',
        language:  'zh-CN',
        autoclose:true,
        clearBtn: true,//清除按钮
        todayBtn: true//今日按钮

    }).on('changeDate',function (ev) {
        vm.xhCoupon['endTime']=$("#endTime").val();
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
                $("#img").val(r.url);
                vm.xhCoupon['img']=r.url;
            }else{
                alert(r.msg);
            }
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		xhCoupon: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.xhCoupon = {};
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
                var url = vm.xhCoupon.id == null ? "sys/xhcoupon/save" : "sys/xhcoupon/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.xhCoupon),
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
                        url: baseURL + "sys/xhcoupon/delete",
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
			$.get(baseURL + "sys/xhcoupon/info/"+id, function(r){
                vm.xhCoupon = r.xhCoupon;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});