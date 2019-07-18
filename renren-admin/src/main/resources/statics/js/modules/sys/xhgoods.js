$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/xhgoods/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '商品名称', name: 'name', index: 'name', width: 80 },
			{ label: '商品标题', name: 'title', index: 'title', width: 80 },
			{ label: '商品介绍', name: 'detail', index: 'detail', width: 80 },
			{ label: '单价', name: 'price', index: 'price', width: 80 },
			{ label: '销量', name: 'sales', index: 'sales', width: 80 },
            { label: '原价', name: 'originalPrice', index: 'original_price', width: 80 },
            { label: '类别', name: 'catId', index: 'cat_id', width: 80 },
            { label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
                    return value === 0 ?
                        '<span class="label label-success">上架</span>' :
                        '<span class="label label-danger">下架</span>';
                }},
            { label: '排序', name: 'sort', index: 'sort', width: 80 },
            // { label: '缩略图', name: 'coverPic', index: 'cover_pic', width: 80 },
            { label: '缩略图', name: 'coverPic', width: 80, formatter: function(value, options, row){
                    return   '<img src='+value+' style="height:100px;width=100px" />';
                }},
            // { label: '大图', name: 'picture', index: 'picture', width: 80 },
            { label: '商品图', name: 'picture', width: 80, formatter: function(value, options, row){
                    return   '<img src='+value+' style="height:100px;width=100px" />';
                }},
            { label: '添加时间', name: 'addTime', index: 'add_time', width: 80 },
            { label: '门店', name: 'deptId', index: 'shop_id', width: 80 },

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
                $("#coverPic").val(r.url);
                vm.xhGoods['coverPic']=r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#upload1', {
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
                $("#picture").val(r.url);
                vm.xhGoods['picture']=r.url;
            }else{
                alert(r.msg);
            }
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		xhGoods: {
            deptId:null,
            deptName:null,
        }
	},
	methods: {
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept//ParentList", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.xhGoods.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.xhGoods.deptName = node.name;
                }
            })
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.xhGoods = {deptName:null, deptId:null};

            vm.getDept();
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

                var url = vm.xhGoods.id == null ? "sys/xhgoods/save" : "sys/xhgoods/update";

                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.xhGoods),
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
                        url: baseURL + "sys/xhgoods/delete",
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
			$.get(baseURL + "sys/xhgoods/info/"+id, function(r){
                vm.xhGoods = r.xhGoods;
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
                    vm.xhGoods.deptId = node[0].deptId;
                    vm.xhGoods.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
        checkImg:function (val) {
            var img_id = val;
            var index= img_id.indexOf(".");
            img_id=img_id.substring(index);
            if(img_id!=".bmp"&&img_id!=".png"&&img_id!=".gif"&&img_id!=".jpg"&&img_id!=".jpeg"){  //根据后缀，判断是否符合图片格式
                alert("不是指定图片格式,重新选择");
                $("#fileSelect").val("");
                $('#btnSaveOrUpdate').button('reset');
                $('#btnSaveOrUpdate').dequeue();
                return 0;
            }else{
                return 1;
            }

        }


    }
});