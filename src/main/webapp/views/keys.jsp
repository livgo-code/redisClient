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
                <li><a href="#">自定义</a></li>
            </ul>
            <%--<div class="row-fluid sortable">--%>
                <%--<div class="box span12">--%>
                    <%--<div class="box-header" data-original-title>--%>
                        <%--<h2><i class="halflings-icon edit"></i><span class="break"></span>Add</h2>--%>
                    <%--</div>--%>
                    <%--<div class="box-content">--%>
                        <%--<form class="form-horizontal">--%>
                            <%--<fieldset>--%>
                                <%--<div class="control-group">--%>
                                    <%--<label class="control-label" for="key">Key</label>--%>

                                    <%--<div class="controls">--%>
                                        <%--<input class="input-xlarge focused" id="key" type="text"--%>
                                               <%--value="This is key…">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="control-group">--%>
                                    <%--<label class="control-label" for="name">Name</label>--%>

                                    <%--<div class="controls">--%>
                                        <%--<input class="input-xlarge focused" id="name" type="text"--%>
                                               <%--value="This is name…">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-actions">--%>
                                    <%--<button type="submit" class="btn btn-primary">Save</button>--%>
                                    <%--<button class="btn">Cancel</button>--%>
                                <%--</div>--%>
                            <%--</fieldset>--%>
                        <%--</form>--%>

                    <%--</div>--%>
                <%--</div>--%>
                <%--<!--/span-->--%>

            <%--</div>--%>
            <h5 style="color: #808080" >自定义数据需要在kv.properties里定义</h5>
            <div class="row-fluid sortable">
                <div class="box span12">
                    <div class="box-header">
                        <h2><i class="halflings-icon align-justify"></i><span class="break"></span>Key</h2>
                    </div>
                    <div class="box-content">
                        <table class="table table-bordered table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>No</th>
                                <th>Key</th>
                                <th>Name</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${keyBeans}" var="keyBean" varStatus="no">
                                <tr>
                                    <td>${no.index + 1}</td>
                                    <td class="center">${keyBean.key}</td>
                                    <td class="center">${keyBean.name}</td>
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
