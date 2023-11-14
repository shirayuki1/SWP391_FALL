<%-- 
    Document   : manageRequest
    Created on : Oct 11, 2023, 3:58:37 PM
    Author     : Admin
--%>

<%@page import="Model.Account"%>
<%@page import="Dao.AccountDAO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Quản lý đơn đặt hàng</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script type="text/javascript" src="scripts/jquery.formatCurrency.js"></script>

    </head>

    <body onload="time()" class="app sidebar-mini rtl">

        <jsp:include page="managerpage.jsp"/>
        <%
            Account user = ((Account) session.getAttribute("userlogin"));
            int staffID = 0;
            if (!(user.getRoleid().getName().equals("manager"))) {
                request.getRequestDispatcher("Unauthorized.jsp").forward(request, response);
            } else {
                staffID = user.getId();
            }

            String[] status = {"Đang chờ xét duyệt", "Đã xét duyệt", "Đơn đặt hàng sẽ được thực hiện", "Đã từ chối"};
            request.setAttribute("statusArray", status);
        %>
        <main class="app-content" >
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách đơn đặt hàng</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">




                                <table class="table table-hover table-bordered" id="sampleTable">
                                    <thead>
                                        <tr>
                                            <th width="10"><input type="checkbox" id="all"></th>
                                            <th>Mã đơn hàng</th>
                                            <th>Khách hàng</th>
                                            <th>Số điện thoại</th>
                                            <th>Địa chỉ </th>

                                            <th>Khuyễn mãi</th>
                                            <th>Tổng sản phẩm</th>
                                            <th>Tổng tiền</th>
                                            <th>Thời gian</th>
                                            <th>Tình trạng</th>



                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${listAcc}" var="i">
                                            <tr>

                                                <td width="10"><input type="checkbox" name="check1" value="1"></td>
                                                <td>${i.id}</td>
                                                <td>${i.lastname}</td>
                                                <td>${i.phone}</td>
                                                <td>${i.adress}</td>

                                                <td>${i.promotionname}</td>
                                                <td>${i.totalItems}</td>
                                                <td>${i.totalPrice}</td>
                                                <td>${i.orderDate}</td>
                                                <!--<td>${i.orderStatus}</td>-->
                                                <td>
                                                    <form action="UpdateOrder" method="POST">
                                                        <select name="orderStatus" onchange="this.form.submit()">
                                                            <option value="">${i.orderStatus}</option>
                                                            <option value="Đợi xét duyệt" ${i.orderStatus == 'Đợi xét duyệt' ? 'disabled' : ''}>Đợi xét duyệt</option>
                                                            <option value="Đang giao hàng" ${i.orderStatus == 'Đang giao hàng' ? 'disabled' : ''}>Đang giao hàng</option>
                                                            <option value="Thành công" ${i.orderStatus == 'Thành công' ? 'disabled' : ''}>Thành công</option>
                                                            <option value="Từ chối" ${i.orderStatus == 'Từ chối' ? 'disabled' : ''}>Từ chối</option>
                                                        </select>
                                                        <input type="hidden" name="orderId" value="${i.id}">
                                                        <input type="hidden" name="staffId" value="<%= staffID%>">
                                                    </form>
                                                </td>






                                                <td>
                                                    <a href="OrderDetail?orderID=${i.id}&user=<%= user.getRoleid().getName()%>">
                                                        <button class="btn btn-primary btn-sm edit" type="button" title="chi tiết" id="show-emp" data-toggle="modal" data-target="#ModalUP"><i class="fas fa-edit"></i></button>
                                                    </a>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!--
          MODAL
        -->
        <div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
             data-keyboard="false">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group  col-md-12">
                                <span class="thong-tin-thanh-toan">
                                    <h5>Quản lý đơn đặt hàng</h5>
                                </span>
                            </div>
                        </div>
                        <form action="UpdateRequest" method="POST" id="updatesp">


                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>
        <!--
        MODAL
        -->

        <!-- Essential javascripts for application to work-->
        <script src="./js/jquery-3.2.1.min.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="./js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="./js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">$('#sampleTable').DataTable();</script>
    </body>

</html>
