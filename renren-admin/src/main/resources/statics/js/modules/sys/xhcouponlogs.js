$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/xhcouponlogs/list',
        datatype: "json",
        colModel: [
			{ label: '买家ID', name: 'buyerName', index: 'buyer_id', width: 80 },
			{ label: '分享ID', name: 'shareName', index: 'share_id', width: 80 },
			{ label: '优惠券id', name: 'couponReceiveId', index: 'coupon_receive_id', width: 80 }, 			
			{ label: '订单号', name: 'orderNumber', index: 'order_number', width: 80 }, 			
			{ label: '原订单金额', name: 'orderOriginalAmount', index: 'order_original_amount', width: 80 }, 			
			{ label: '优惠券的金额', name: 'couponAmount', index: 'coupon_amount', width: 80 }, 			
			{ label: '抵扣优惠券之后的订单金额', name: 'orderFinalAmount', index: 'order_final_amount', width: 80 }, 			
			{ label: '领取时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '日志状态', name: 'status', index: 'status', width: 80 , formatter: function(value, options, row){
                    return value === 0 ?
                        '<span class="label label-success">未支付</span>' :
                        '<span class="label label-danger">支付完成</span>';
                }},
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		xhCouponLogs: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.xhCouponLogs = {};
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
                var url = vm.xhCouponLogs.id == null ? "sys/xhcouponlogs/save" : "sys/xhcouponlogs/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.xhCouponLogs),
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
                        url: baseURL + "sys/xhcouponlogs/delete",
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
			$.get(baseURL + "sys/xhcouponlogs/info/"+id, function(r){
                vm.xhCouponLogs = r.xhCouponLogs;
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