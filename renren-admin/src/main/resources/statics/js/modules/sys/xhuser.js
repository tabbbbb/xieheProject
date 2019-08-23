$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/xhuser/list',
        datatype: "json",
        colModel: [			
			// { label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '昵称', name: 'nikName', index: 'nik_name', width: 80 }, 			
			{ label: '性别', name: 'gender', index: 'gender', width: 80 }, 			
			{ label: '用户类型', name: 'userType',width: 80, formatter: function(value, options, row){
                    return value === 0 ?
                        '<span class="label label-success">用户</span>' :
                        '<span class="label label-danger">销售</span>';
                }},
			{ label: '销售昵称', name: 'sellName', index: 'sell_id', width: 80 },
			{ label: '公司名称', name: 'shopName', index: 'shop_id', width: 80 },
			{ label: '会员昵称', name: 'parentName', index: 'parent_name', width: 80 },
			{ label: '积分', name: 'point', index: 'point', width: 80 }, 			
			{ label: '会员等级', name: 'level', index: 'level', width: 80 }, 			
			{ label: '电话号码', name: 'phone', index: 'phone', width: 80 }
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

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var settingSell = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            name:"nikName",
            url:"nourl"
        }
    }
};

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            phone:null,
            shopName:null
        },
		showList: true,
		title: null,
		xhUser: {
            shopId:null,
            shopName:null,
            sellId:null,
            sellName:null
        }
	},
	methods: {
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/ParentList", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.xhUser.shopId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.xhUser.shopName = node.name;
                    // alert(vm.xhUser.shopName);
                }
            })
        },
        getSell: function(){
            //加载类目
            $.get(baseURL + "sys/xhuser/ParentList", function(r){
                ztreeDict = $.fn.zTree.init($("#sellTree"), settingSell, r);
                var node = ztreeDict.getNodeByParam("id", vm.xhUser.sellId);
                if(node != null){
                    ztreeDict.selectNode(node);
                    vm.xhUser.sellName = node.nikName;
                }
            })
        },
		query: function () {
			vm.reload();
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
                var url = vm.xhUser.id == null ? "sys/xhuser/save" : "sys/xhuser/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.xhUser),
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
                        url: baseURL + "sys/xhuser/delete",
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
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择门店",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.xhUser.shopId = node[0].deptId;
                    vm.xhUser.shopName = node[0].name;
                    layer.close(index);
                }
            });
        },
        sellTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择销售",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#sellLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztreeDict.getSelectedNodes();
                    //选择上级部门
                    vm.xhUser.sellId = node[0].id;
                    vm.xhUser.sellName = node[0].nikName;
                    layer.close(index);
                }
            });
        },
		getInfo: function(id){
			$.get(baseURL + "sys/xhuser/info/"+id, function(r){
                vm.xhUser = r.xhUser;
                vm.getDept();
                vm.getSell();
            });

		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'shopName': vm.q.shopName,
                    'phone': vm.q.phone
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});