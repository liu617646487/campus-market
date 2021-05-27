<style>
    .select-icons{
         display:inline-block;
         width:32px;
         height:24px;
         text-align:center;
         vertical-align:middle;
         cursor:pointer;
         line-height:24px;
         font-size: 28px;
     }
    .selected-icons{
        border: 1px green solid;
    }
</style>

<div class="modal fade" id="icons-panel" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myLargeModalLabel">icons选择面板</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped">
                                        <tbody id="icons-tbody">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">

                <button type="button" id="confirm-icon-btn" class="btn btn-primary">确认</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/admin/js/icons.js"></script>
<script type="text/javascript">

    $(document).ready(function() {
        //js调用图标常量库
        showIcons();

    });

    $(document).on('click','.select-icons',function () {
        $(".select-icons").removeClass("selected-icons")
        $(this).addClass("selected-icons")
    });
    $(document).on('dblclick','.select-icons',function () {
        getSelectedIcon();
    });


   function showIcons() {
        var html = '';
        for (var i=0;i<all_system_icons.length;i++){
            var tr = '';
            if (i == 0){
                tr = '<tr align="center">';
            }
            tr += '<td><i class="mdi '+all_system_icons[i]+' select-icons" val="'+all_system_icons[i]+'"></i></td>';
            //debugger;
            if ((i+1)%15==0){
                tr += '</tr><tr align="center">';
            }
            html += tr;
        }
        html += '</tr>';
        $("#icons-tbody").append(html);
    }

    //$("#icons-tbody").children("div span").remove()

</script>