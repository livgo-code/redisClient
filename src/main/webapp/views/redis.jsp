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
                <li><a href="#">查询</a></li>
            </ul>
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon edit"></i><span class="break"></span>Select</h2>
                    </div>
                    <div class="box-content">
                        <form class="form-horizontal" action="/home/redis/result" method="POST">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label">Server</label>

                                    <div class="controls">
                                        <c:forEach items="${servers}" var="server" varStatus="no">
                                            <label class="checkbox inline">
                                                <input type="checkbox" id="serverIds" name="serverIds"
                                                       value="${server.id}">
                                                <b>(ID:${server.id})</b>${server.ip}:${server.port}
                                            </label>
                                        </c:forEach>
                                        </label>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="key">Key</label>

                                    <div class="controls">
                                        <input class="input-xlarge focused" id="key" type="text" name="key"
                                               value="${key==null ? 'Input a key...' : key}">
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">Search</button>
                                </div>
                            </fieldset>
                        </form>

                    </div>
                </div>
                <!--/span-->

            </div>
            <!--/row-->
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header">
                        <h2><i class="halflings-icon align-justify"></i><span class="break"></span>Result</h2>
                    </div>
                    <div class="box-content">
                        <table class="table table-bordered table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>No</th>
                                <th>ServerID</th>
                                <th>Type</th>
                                <th>Key</th>
                                <th>Name</th>
                                <th>Size</th>
                                <th>Value</th>
                                <th>TTL</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${redisBeans}" var="redisBeans" varStatus="no">

                                <tr>
                                    <td>${no.index + 1}</td>
                                    <td class="center">${redisBeans.serverId}</td>
                                    <td class="center">${redisBeans.rtype}</td>
                                    <td class="center">${redisBeans.rkey}</td>
                                    <td class="center">${redisBeans.rname}</td>
                                    <td class="center">${redisBeans.rsize}</td>
                                    <td class="center">
                                        <c:choose>
                                            <c:when test="${redisBeans.rtype != 'KV'}">
                                                <a href="${ctx}/home/redis/detail?serverId=${redisBeans.serverId}&key=${redisBeans.rkey}&type=${redisBeans.rtype}&size=${redisBeans.rsize}"><span class="label label-success">查看</span></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${ctx}/home/redis/detail?serverId=${redisBeans.serverId}&key=${redisBeans.rkey}&type=${redisBeans.rtype}&size=${redisBeans.rsize}"><span
                                                        class="label label-success">${redisBeans.rvalue}</span></a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="center">${redisBeans.rexpire}</td>
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
