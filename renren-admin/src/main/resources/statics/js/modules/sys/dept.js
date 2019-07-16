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
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        dept:{
            parentName:null,
            parentId:0,
            orderNum:0
        }
    },
    methods: {
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/select", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
                var node = ztree.getNodeByParam("deptId", vm.dept.parentId);
                ztree.selectNode(node);

                vm.dept.parentName = node.name;
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.dept = {parentName:null,parentId:0,orderNum:0};
            vm.getDept();
        },
        update: function () {
            var deptId = getDeptId();
            if(deptId == null){
                return ;
            }

            $.get(baseURL + "sys/dept/info/"+deptId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.dept = r.dept;

                vm.getDept();
            });
        },
        del: function () {
            var deptId = getDeptId();
            if(deptId == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/dept/delete",
                    data: "deptId=" + deptId,
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.dept.deptId == null ? "sys/dept/save" : "sys/dept/update";
            var picture = vm.checkImg($('#picture').val());
            if(picture==0){
                return;
            }
            var formData = new FormData();
            formData.append("picture", $('#picture')[0].files[0]);
            formData.append("data",JSON.stringify(vm.dept));
            //发送文件数据
            $.ajax({
                url: baseURL + url,
                dataType: 'json',
                type: 'POST',
                cache: false, //上传文件不需要缓存
                data: formData,
                processData: false, // 告诉jQuery不要去处理发送的数据
                contentType: false, // 告诉jQuery不要去设置Content-Type请求头
                success: function (data) {
                    if (data.code == 0) {
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(data.msg);
                    }
                },
                error: function (response) {
                    console.log(response);
                }
            });
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.dept.parentId = node[0].deptId;
                    vm.dept.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            Dept.table.refresh();
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

var Dept = {
    id: "deptTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dept.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '部门ID', field: 'deptId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '部门名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级部门', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '排序号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '门店图片', field: 'shopPic', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '门店积分', field: 'shopPoint', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '门店地址', field: 'address', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '门店电话', field: 'telephone', align: 'center', valign: 'middle', sortable: true, width: '100px'}
    ]
    return columns;
};


function getDeptId () {
    var selected = $('#deptTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return null;
    } else {
        return selected[0].id;
    }
}


$(function () {
    $.get(baseURL + "sys/dept/info", function(r){
        var colunms = Dept.initColumn();
        var table = new TreeTable(Dept.id, baseURL + "sys/dept/list", colunms);
        table.setRootCodeValue(r.deptId);
        table.setExpandColumn(2);
        table.setIdField("deptId");
        table.setCodeField("deptId");
        table.setParentCodeField("parentId");
        table.setExpandAll(false);
        table.init();
        Dept.table = table;
    });
});
