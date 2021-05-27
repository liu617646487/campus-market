<nav class="ease2">
    <ul>
        <li class="blank-head">
            <a href="javascript:;"></a>
        </li>

        <li class="category-12 catg">
            <a href="/home/index/index" target="_top">
                <i class="nav-icons">
                    <img  src="/home/imgs/12.png" alt="全部商品"></i>
                <h3>全部商品</h3>
            </a>
        </li>
        <#if goodsCategories??>
            <#list goodsCategories as goodsCategorie>
                <#if !(goodsCategorie.parent??)>
                    <li class="category-${goodsCategorie.id} catg">
                        <a href="/home/goods/list?cid=${goodsCategorie.id}" target="_top">
                            <i class="nav-icons">
                                <img src="/photo/view?filename=${goodsCategorie.icon}" alt="${goodsCategorie.name}">
                            </i>
                            <h3>${goodsCategorie.name}</h3>
                        </a>
                        <div class="sub-nav">
                            <span>
                                <#list goodsCategories as secondgoodsCategorie>
                                    <#if secondgoodsCategorie.parent?? && secondgoodsCategorie.parent.id==goodsCategorie.id>
                                        <a href="/home/goods/list?cid=${secondgoodsCategorie.id}" target="_top">${secondgoodsCategorie.name}</a>
                                    </#if>
                                </#list>
                            </span>
                        </div>
                    </li>
                </#if>
            </#list>
        </#if>
    </ul>
</nav>