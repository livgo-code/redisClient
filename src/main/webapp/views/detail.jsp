<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../head.jsp"></jsp:include>

<div >
    <div >

        <jsp:include page="../menu.jsp"></jsp:include>
        <!-- start: Content -->
        <div id="content" class="span10">


            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="">Home</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li><a href="#">详情</a></li>
            </ul>
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header">
                        <h2><i class="halflings-icon align-justify"></i><span class="break"></span>Key</h2>
                    </div>
                    <div class="box-content">
                        <table class="table table-bordered table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>ServerID</th>
                                <th>Key</th>
                                <th>Type</th>
                                <th>Size</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${redisBeans}" var="redisBeans" varStatus="no">

                            </c:forEach>
                            <tr>
                                <td>${redisDetailBean.serverId}</td>
                                <td class="center">${redisDetailBean.key}</td>
                                <td class="center">${redisDetailBean.type}</td>
                                <td class="center">${redisDetailBean.size}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--/span-->
            </div>
            <div class="controls">
                <form class="form-horizontal" action="${ctx}/home/redis/detail/pattern/map" method="POST">
                    <fieldset>
                        <input type="hidden" name="serverId" value="${redisDetailBean.serverId}">
                        <input type="hidden" name="key" value="${redisDetailBean.key}">
                        <input type="hidden" name="type" value="${redisDetailBean.type}">
                        <input type="hidden" name="size" value="${redisDetailBean.size}">
                        <input class="input-xlarge focused" id="k" type="text" name="k"
                               value="Input a mapKey…">
                        <button type="submit" class="btn btn-primary btn-small" >Search</button>
                    </fieldset>
                </form>

            </div>
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header">
                        <h2><i class="halflings-icon align-justify"></i><span class="break"></span>Result</h2>
                    </div>
                    <div class="box-content">
                        <table class="table table-bordered table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>K</th>
                                <th>Length</th>
                                <th>V</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${redisDetailBean.keyValBeans}" var="keyValBean" varStatus="no">
                                <tr>
                                    <td>${keyValBean.key}</td>
                                    <td class="center">${keyValBean.len}</td>
                                    <td class="center">${keyValBean.val}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--/span-->
            </div>
            <!--/row-->


        </div>
        <!--/.fluid-container-->

        <!-- end: Content -->
    </div>
    <!--/#content.span10-->
</div>


<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
