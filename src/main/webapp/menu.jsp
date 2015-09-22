<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- start: Main Menu -->
<div id="sidebar-left" class="span2">
    <div class="nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li><a href="${ctx}/home/index"><i class="icon-star"></i><span
                    class="hidden-tablet"> 首页</span></a></li>
            <li><a href="${ctx}/home/key/get"><i class="icon-file-alt"></i><span class="hidden-tablet"> 命名查询</span></a>
            </li>
            <li><a href="${ctx}/home/monitor/index"><i class="icon-picture"></i><span class="hidden-tablet"> 镜像匹配</span></a>
            </li>
            <li><a href="${ctx}/home/redis/index"><i class="icon-eye-open"></i><span class="hidden-tablet"> KEY查询</span></a>
            </li>
        </ul>
    </div>
</div>
<!-- end: Main Menu -->
<noscript>
    <div class="alert alert-block span10">
        <h4 class="alert-heading">Warning!</h4>

        <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
            enabled to use this site.</p>
    </div>
</noscript>