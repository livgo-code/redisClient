<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../head.jsp"></jsp:include>

<%--<div class="container-fluid-full">--%>
    <%--<div class="row-fluid">--%>
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
                <li><a href="#">镜像</a></li>
            </ul>
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header" data-original-title>
                        <h2><i class="halflings-icon edit"></i><span class="break"></span>Select</h2>
                    </div>
                    <div class="box-content">
                        <form class="form-horizontal" action="${ctx}/home/monitor/pattern" method="POST">
                            <fieldset>

                                <div class="control-group">
                                    <label class="control-label" for="serverId">Server</label>

                                    <div class="controls">
                                        <select id="serverId" name="serverId">
                                            <c:forEach items="${servers}" var="server" varStatus="no">
                                                <option value="${server.id}">
                                                    <b>(ID:${server.id})</b>${server.ip}:${server.port}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="pattern">Pattern</label>

                                    <div class="controls">
                                        <input class="input-xlarge focused" id="pattern" type="text" name="pattern"
                                               value="${pattern==null ? 'Input a regular expression...' : pattern}"><h5 style="color: #808080" >例如：xx*</h5>
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
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header">
                        <h2><i class="halflings-icon align-justify"></i><span class="break"></span>Key</h2>
                    </div>
                    <div class="box-content">
                        <table class="table table-bordered table-striped table-condensed ">
                            <thead>
                            <tr>
                                <th>No</th>
                                <th>Key</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${keySet}" var="keySet" varStatus="no">
                                <tr>
                                    <td>${no.index + 1}</td>
                                    <td class="center">${keySet}</td>
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
