<style>
    .loginandrigist {
        margin-left: 1000px;
        margin-top: -66px;
    }

    #person_info {
        height: 100%;
        cursor: pointer;
        position: relative;
    }
    main.css?v=201506100001:20
    .clearfix {
        zoom: 1;
    }
</style>

<header class="ease2">
    <a href="/home/index/index">
        <img class="logo ease2" src="/home/imgs/index_logo.png" alt="郑州财经学院跳蚤市场">
    </a>
    <div class="header-main center ease2">
        <a href="/home/index/index" class="slogan">
            <h1 class="s-main"></h1>
            <div class="s-submain"></div>
            <img src="/home/imgs/2shoujie_web_title.png" alt="郑州财经学院跳蚤市场">
        </a>
        <div class="search-box-wr ease2">
            <div class="search-box center" >
                <button  class="search-submit" id="search-button" onclick="searchPro()">搜索</button>
                <div class="input-wr">
                    <img class="search-icon" src="/home/imgs/search-icon.png">
                    <div class="search-input">
                        <input name="keyword" id="keyword" x-webkit-speech="" placeholder="搜索你想要的商品" value="${name!""}" type="text">
                    </div>
                </div>
            </div>

            <div class="search-hots center ease2">
                <span>热门：</span>
                <#if goodsCategories??>
                    <#list goodsCategories as hotgoodsCategorie>
                        <#if !(hotgoodsCategorie.parent??)>
                            <a class="hots" href="/home/goods/list?cid=${hotgoodsCategorie.id}" target="_top">${hotgoodsCategorie.name}</a>
                        </#if>
                        <#if hotgoodsCategorie_index == 20>
                            <#break >
                        </#if>
                    </#list>
                </#if>

            </div>
        </div>

        <div class="ease2 log-reg" id="have-not-login">
            <#if Session["zzcjxy_student"]??>
                <div id="have_login" class="clearfix">
                    <div id="person_info" class="clearfix">
                        <a href="../student/index">
                            <#if Session["zzcjxy_student"].headPic??>
                                <img class="avatar" style="height:48px;width:48px;" src="/photo/view?filename=${Session["zzcjxy_student"].headPic}" alt="头像">
                            <#else>
                                <img class="avatar" style="height:48px;width:48px;" src="/home/imgs/avatar1.png" alt="头像">
                            </#if>
                        </a>
                        <div style="display:inline;" class="person_name">
                            <a href="../student/index" id="id-btn">Hi:${Session["zzcjxy_student"].nickname!Session['zzcjxy_student'].sno}</a>
                        </div>
                        <div style="display:inline;" class="person_name">
                            <a href="../index/logout" id="log-btn">&nbsp;&nbsp;&nbsp;&nbsp;退出</a>
                        </div>
                    </div>
                </div>
            <#else>
                <!-- loginandrigist -->
                <div class="button" ><a href="../index/login">登录/注册</a></div>
            </#if>
        </div>


    </div>
</header>

<script type="text/javascript">
    function searchPro(){
        var keyword=$("#keyword").val()
        window.location.href="/home/index/index?name="+keyword;
    }
</script>