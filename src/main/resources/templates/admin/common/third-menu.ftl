<div class="toolbar-btn-action">
    <#if userThirdMenus??>
        <#list userThirdMenus as userThirdMenu>
            <#if userThirdMenu.isshow>
                <#if !userThirdMenu.isbutton>
                    <a class="btn btn-primary m-r-5" href="${userThirdMenu.url}"><i class="mdi ${userThirdMenu.icon}"></i> ${userThirdMenu.name}</a>
                <#else>
                    <a class="btn btn-primary m-r-5" href="javascript:${userThirdMenu.url}"><i class="mdi ${userThirdMenu.icon}"></i> ${userThirdMenu.name}</a>
                </#if>
            </#if>
        </#list>
    </#if>
</div>