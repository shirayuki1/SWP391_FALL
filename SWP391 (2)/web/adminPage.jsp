

<%@page import="Model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <link href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
        <link href="https://cdn.datatables.net/datetime/1.1.2/css/dataTables.dateTime.min.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script type="text/javascript" src="scripts/jquery.formatCurrency.js"></script>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
            }
        </style>
    </head>
    <body>
        <%
            Account user = ((Account) session.getAttribute("userlogin"));
            if (!(user.getRoleid().getName().equals("admin"))) {
                request.getRequestDispatcher("Unauthorized.jsp").forward(request, response);
            }
        %>
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                <!-- User Menu-->
                <li><a class="app-nav__item" href="Logout"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user">
              <a href="UserServlet?action=profile">
            <img style="border-radius: 50%;" class="app-sidebar__user-avatar rounded" src="img/${sessionScope.userlogin.image}" width="100px" alt="User Image">
                </a>
                <div>
                    <p class="app-sidebar__user-name"><b></b></p>
                    <p class="app-sidebar__user-designation">
                        <font>${sessionScope.userlogin.firstname}</font>
                    </p>
                </div>
         
            </div>

            <hr>
            <ul class="app-menu">
                <!--                <li><a class="app-menu__item haha" href="phan-mem-ban-hang.html"><i class='app-menu__icon bx bx-cart-alt'></i>
                                        <span class="app-menu__label">POS Bán Hàng</span></a></li>
                                <li><a class="app-menu__item " href="index.html"><i class='app-menu__icon bx bx-tachometer'></i><span
                                            class="app-menu__label">Bảng điều khiển</span></a></li>
                                <li><a class="app-menu__item " href="table-data-table.html"><i class='app-menu__icon bx bx-id-card'></i>
                                        <span class="app-menu__label">Quản lý nhân viên</span></a></li>
                                <li><a class="app-menu__item " href="#"><i class='app-menu__icon bx bx-user-voice'></i><span
                                            class="app-menu__label">Quản lý khách hàng</span></a></li>-->
                <li><a class="app-menu__item" href="ManagerAllAccount"><i
                            class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lý tài khoản công ty</span></a>
                </li>
                <li><a class="app-menu__item" href="ManagerCustomerAccount"><i
                            class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lý tài khoản người dùng</span></a>
                </li>
                
               
                <!--                <li><a class="app-menu__item" href="table-data-banned.html"><i class='app-menu__icon bx bx-run'></i><span
                                           class="app-menu__label">Quản lý nội bộ
                                       </span></a></li>
                               <li><a class="app-menu__item" href="table-data-money.html"><i class='app-menu__icon bx bx-dollar'></i><span
                                           class="app-menu__label">Bảng kê lương</span></a></li>
                -->                            

               









                <!--<li><a class="app-menu__item" href="page-calendar.html"><i class='app-menu__icon bx bx-calendar-check'></i><span
                class="app-menu__label">Lịch công tác </span></a></li>
    <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Cài
                đặt hệ thống</span></a></li>-->
            </ul>
        </aside>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var currentUrl = window.location.href;

                // Tìm phần tử li có href tương ứng với URL hiện tại và thêm lớp active
                var navItems = document.querySelectorAll(".app-menu__item");
                navItems.forEach(function (item) {
                    if (item.href === currentUrl) {
                        item.classList.add("active");
                    }
                });
            });
        </script>
    </body>
</html>
