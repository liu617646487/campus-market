<script type="text/javascript" src="/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="/admin/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script src="/admin/js/common.js"></script>
<!--对话框-->
<script src="/admin/js/jconfirm/jquery-confirm.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        var currentUrl = window.location.pathname
        var curs = currentUrl.split("/");
        currentUrl = curs[2]
        $(".selected-menu").each(function (i,e) {
            if ($(e).children("a").attr("href").split("/")[2]==currentUrl){
                $(e).addClass('active');
                $(e).parent("ul").parent("li").addClass('active open')
            }
        })
    });
</script>


