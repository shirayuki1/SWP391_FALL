
<%@page import="Model.Account"%>
<%-- 
    Document   : createRequest
    Created on : Oct 3, 2023, 2:53:28 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Create Request</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <!--        <link rel="stylesheet" type="text/css" href="./css/admin.css">-->
        <!-- Font-icon css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.1/css/jquery.dataTables.min.css">

        <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
        <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
        <script>
            function readURL(input, thumbimage) {
                if (input.files && input.files[0]) { // Sử dụng cho Firefox - Chrome
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("#thumbimage").attr('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else { // Sử dụng cho IE
                    $("#thumbimage").attr('src', input.value);
                }
                $("#thumbimage").show();
                $('.filename').text($("#uploadfile").val());
                $('.Choicefile').css('background', '#14142B');
                $('.Choicefile').css('cursor', 'default');
                $(".removeimg").show();
                $(".Choicefile").unbind('click');
            }

            $(document).ready(function () {
                $(".Choicefile").bind('click', function () {
                    $("#uploadfile").click();
                });
                $(".removeimg").click(function () {
                    $("#thumbimage").attr('src', '').hide();
                    $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
                    $(".removeimg").hide();
                    $(".Choicefile").bind('click', function () {
                        $("#uploadfile").click();
                    });
                    $('.Choicefile').css('background', '#14142B');
                    $('.Choicefile').css('cursor', 'pointer');
                    $(".filename").text("");
                });
            })
        </script>

    </head>

    <body class="app sidebar-mini rtl">
        <jsp:include page="headerUser.jsp"/>
        <%
            String customerID = null;
            Account user = ((Account) session.getAttribute("userlogin"));
            if (!(user.getRoleid().getName().equals("manager")) || !(user.getRoleid().getName().equals("staff"))) {
                customerID = String.valueOf(user.getId());
            }
        %>
        <style>
            .Choicefile {
                display: block;
                background: #14142B;
                border: 1px solid #fff;
                color: #fff;
                width: 150px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                padding: 5px 0px;
                border-radius: 5px;
                font-weight: 500;
                align-items: center;
                justify-content: center;
            }

            .Choicefile:hover {
                text-decoration: none;
                color: white;
            }

            #uploadfile,
            .removeimg {
                display: none;
            }

            #thumbbox {
                position: relative;
                width: 100%;
                margin-bottom: 20px;
            }

            .removeimg {
                height: 25px;
                position: absolute;
                background-repeat: no-repeat;
                top: 5px;
                left: 5px;
                background-size: 25px;
                width: 25px;
                /* border: 3px solid red; */
                border-radius: 50%;

            }

            .removeimg::before {
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                content: '';
                border: 1px solid red;
                background: red;
                text-align: center;
                display: block;
                margin-top: 11px;
                transform: rotate(45deg);
            }

            .removeimg::after {
                /* color: #FFF; */
                /* background-color: #DC403B; */
                content: '';
                background: red;
                border: 1px solid red;
                text-align: center;
                display: block;
                transform: rotate(-45deg);
                margin-top: -2px;
            }
        </style>
        <main class="app-content">
            <div class="app-title" style="    display: block;
                 display: -ms-flexbox;
                 display: flex;
                 -webkit-box-align: center;
                 -ms-flex-align: center;
                 align-items: center;
                 -webkit-box-pack: justify;
                 -ms-flex-pack: justify;
                 justify-content: space-between;
                 -webkit-box-orient: horizontal;
                 -webkit-box-direction: normal;
                 -ms-flex-direction: row;
                 flex-direction: row;
                 background-color: #FFF;
                 border-radius: .375rem;
                 padding: 10px 30px;
                 -webkit-box-shadow: 0 1px 2px rgb(0 0 0 / 10%);
                 box-shadow: 0 1px 2px rgb(0 0 0 / 10%);
                 border-left: 6px solid #FFD43B;
                 margin-bottom: 20px;">
                <ul class="app-breadcrumb breadcrumb">
                    <!--                    <li class="breadcrumb-item">Danh sách sản phẩm</li>
                                        <li class="breadcrumb-item"><a href="#">Thêm sản phẩm</a></li>-->
                </ul>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="tile" style="width: 500px;     position: relative;
                             background: #ffffff;
                             border-radius: .375rem;
                             padding: 20px;
                             -webkit-box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
                             box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
                             margin-bottom: 30px;
                             -webkit-transition: all 0.3s ease-in-out;
                             -o-transition: all 0.3s ease-in-out;
                             transition: all 0.3s ease-in-out;">
                            <h3 class="tile-title">Đặt lồng chim</h3>
                            <div class="tile-body">
                                <form action="CreateRequest" method="post" enctype="multipart/form-data" id="createReq">
                                    <input  type="hidden" name="customerID" value="<%= customerID%>">
                                    <div class="form-group">
                                        <label class="control-label">Tên sản phẩm</label>
                                        <input class="form-control" type="text" name="productName" id="tensp">
                                        <div class="error-message" id="tensp-error"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Số lượng</label>
                                        <input class="form-control" type="number" min="0" name="productQuantity" id="quantity">
                                        <div class="error-message" id="quantity-error"></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleSelect1" class="control-label">Danh mục</label>
                                        <select class="form-control" id="exampleSelect2" name="productCategory" required>
                                            <option disabled>-- Chọn loại lồng chim--</option>
                                            <c:forEach items="${requestScope.list}" var="i">
                                                <option value="${i.id}">${i.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleSelect1" class="control-label">Nguyên liệu</label>
                                        <select class="form-control" id="exampleSelect2" name="productMaterial" required>
                                            <option disabled>-- Chọn nguyên liệu --</option>
                                            <c:forEach items="${requestScope.listMaterial}" var="i">
                                                <option value="${i.id}">${i.original}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Hình dáng và kích thước</label>
                                        <input class="form-control" type="text" name="productSize" id="size">
                                        <div class="error-message" id="size-error"></div>
                                    </div>
                                </form>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div style="display: flex;align-items: center;justify-content: center;"> 
                                        <div>
                                            <button style=" background-color: #007bff;
                                                    color: #fff;
                                                    border: none;
                                                    padding: 10px 20px;
                                                    margin-right: 10px;
                                                    cursor: pointer;
                                                    border-radius: 5px;" class="btn btn-save" type="button" onclick="validateForm()">Lưu lại</button>
                                        </div>
                                        <div>
                                            <a style="color: #007bff;
                                               text-decoration: none;
                                               padding: 10px 20px;
                                               border: 1px solid #007bff; /* Add a border */
                                               border-radius: 5px; /* Add rounded corners */
                                               transition: background-color 0.3s, color 0.3s; /* Add a smooth transition */" class="btn btn-cancel" href="homepage.jsp">Hủy bỏ</a>
                                        </div>


                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>




        </main>
        <!--                    <-- MODAL DANH MỤC -->

        <div class="modal fade" id="adddanhmuc" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
             data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body">
                        <form action="createCategory" method="post" >
                            <div class="row">
                                <div class="form-group  col-md-12">
                                    <span class="thong-tin-thanh-toan">
                                        <h5>Thêm mới danh mục </h5>
                                    </span>
                                </div>
                                <div class="form-group col-md-12">
                                    <label class="control-label">Nhập tên danh mục mới</label>
                                    <input class="form-control" type="text" required name="category">
                                </div>
                                <div class="form-group col-md-12">
                                    <label class="control-label">Danh mục sản phẩm hiện đang có</label>
                                    <ul class="compact-list">
                                        <c:forEach items="${requestScope.list}" var="i" varStatus="status">
                                            <div>${status.index + 1}. ${i.name}</div>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>


                            <button class="btn btn-save" type="submit">Lưu lại</button>
                            <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>



                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>



        <style>
            ul.compact-list {
                max-height: 100px; /* Điều chỉnh chiều cao tối đa của danh sách */
                overflow: auto; /* Hiển thị thanh cuộn khi danh sách quá dài */
                padding-left: 20px;
            }
        </style>

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-secondary mt-5 pt-5">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                    <h5 class="text-secondary text-uppercase mb-4">Thông tin liên lạc</h5>
                    <p class="mb-4">Chuyên cung cấp các loại chim</p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Tp.Hồ Chí Minh</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>binhdxse160***@fpt.edu.vn</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+84999999999</p>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="row">
                        <div class="col-md-4 mb-5">
                            <h5 class="text-secondary text-uppercase mb-4">Lồng Shop</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-secondary mb-2" href="homePage.jsp"><i class="fa fa-angle-right mr-2"></i>Trang chủ</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Kênh bán hàng</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Của hàng</a>                               
                                <a class="text-secondary" href="#"><i class="fa fa-angle-right mr-2"></i>Liên lạc với chúng tôi</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h5 class="text-secondary text-uppercase mb-4">Tài khoản của tôi</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Hồ sơ</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Giỏ hàng</a>
                                <a class="text-secondary mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Đơn hàng</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h6 class="text-secondary text-uppercase mt-4 mb-3">Theo dõi chúng tôi</h6>
                            <div class="d-flex">
                                <a class="btn btn-primary btn-square mr-2" href="#"><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-primary btn-square mr-2" href="#"><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-primary btn-square mr-2" href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a class="btn btn-primary btn-square" href="#"><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->




        <script src="./js/jquery-3.2.1.min.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./js/main.js"></script>
        <script src="./js/plugins/pace.min.js"></script>
        <script>
                                                const inpFile = document.getElementById("inpFile");
                                                const loadFile = document.getElementById("loadFile");
                                                const previewContainer = document.getElementById("imagePreview");
                                                const previewImage = previewContainer.querySelector(".image-preview__image");
                                                const previewDefaultText = previewContainer.querySelector(".image-preview__default-text");
                                                inpFile.addEventListener("change", function () {
                                                    const file = this.files[0];
                                                    if (file) {
                                                        const reader = new FileReader();
                                                        previewDefaultText.style.display = "none";
                                                        previewImage.style.display = "block";
                                                        reader.addEventListener("load", function () {
                                                            previewImage.setAttribute("src", this.result);
                                                        });
                                                        reader.readAsDataURL(file);
                                                    }
                                                });
        </script>
        <script>
            function validateForm() {
                var tensp = document.getElementById("tensp").value.trim();
                var quantity = document.getElementById("quantity").value.trim();
                var size = document.getElementById("size").value.trim();

                var isValid = true;

                if (tensp === "") {
                    document.getElementById("tensp").classList.add("error-input");
                    document.getElementById("tensp-error").textContent = "Vui lòng nhập tên sản phẩm.";
                    document.getElementById("tensp-error").style.color = "red";
                    document.getElementById("tensp").style.borderColor = "red";
                    isValid = false;
                } else {
                    document.getElementById("tensp").classList.remove("error-input");
                    document.getElementById("tensp-error").textContent = "";
                    document.getElementById("tensp-error").style.color = "";
                    document.getElementById("tensp").style.borderColor = "";
                }

                if (quantity === "") {
                    document.getElementById("quantity").classList.add("error-input");
                    document.getElementById("quantity-error").textContent = "Vui lòng nhập số lượng.";
                    document.getElementById("quantity-error").style.color = "red";
                    document.getElementById("quantity").style.borderColor = "red";
                    isValid = false;
                } else if (quantity <= 0) {
                    document.getElementById("quantity").classList.add("error-input");
                    document.getElementById("quantity-error").textContent = "Số lượng vui lòng lớn hơn 0.";
                    document.getElementById("quantity-error").style.color = "red";
                    document.getElementById("quantity").style.borderColor = "red";
                    isValid = false;

                } else {
                    document.getElementById("quantity").classList.remove("error-input");
                    document.getElementById("quantity-error").textContent = "";
                    document.getElementById("quantity-error").style.color = "";
                    document.getElementById("quantity").style.borderColor = "";
                }
                if (size === "") {
                    document.getElementById("size").classList.add("error-input");
                    document.getElementById("size-error").textContent = "Vui lòng nhập hình dang va kích thước.";
                    document.getElementById("size-error").style.color = "red";
                    document.getElementById("size").style.borderColor = "red";
                    isValid = false;
                } else {
                    document.getElementById("size").classList.remove("error-input");
                    document.getElementById("size-error").textContent = "";
                    document.getElementById("size-error").style.color = "";
                    document.getElementById("size").style.borderColor = "";
                }

                // Validate the image field


                if (isValid) {
                    document.getElementById("createReq").submit();
                }
            }
        </script>

        <script>
            <% if (request.getAttribute("message") != null) {%>
            swal("<%= request.getAttribute("message")%>", "", "success");
            <% request.removeAttribute("message"); %>
            <% }%>
        </script>

        <script>
            <% if (request.getAttribute("mess1") != null) {%>
            swal("<%= request.getAttribute("mess1")%>", "", "success");
            <% request.removeAttribute("mess1"); %>
            <% } %>
        </script>
        <script>
            <% if (request.getAttribute("mess2") != null) {%>
            swal("<%= request.getAttribute("mess2")%>", "", "error");
            <% request.removeAttribute("mess2"); %>
            <% }%>
        </script>

    </body>

</html>

