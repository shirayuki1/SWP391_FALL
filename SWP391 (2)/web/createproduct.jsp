<%-- 
    Document   : createProduct
    Created on : Sep 23, 2023, 8:20:19 PM
    Author     : Admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Add product</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <jsp:include page="managerpage.jsp"/>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item">Danh sách sản phẩm</li>
                    <li class="breadcrumb-item"><a href="#">Thêm sản phẩm</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Tạo mới sản phẩm</h3>
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">
                                    <a class="btn btn-add btn-sm" data-toggle="modal" data-target="#adddanhmuc"><i class="fas fa-folder-plus"></i> Thêm danh mục</a>
                                </div>
                                <div class="col-sm-2">
                                    <a class="btn btn-add btn-sm" data-toggle="modal" data-target="#addmaterial"><i class="fas fa-folder-plus"></i> Thêm nguyên liệu</a>
                                </div>
                            </div>
                            <form class="row" action="createProduct" method="post" enctype="multipart/form-data" id="createPro">
                                <div class="form-group col-md-4">
                                    <label class="control-label">Tên sản phẩm</label>
                                    <input class="form-control" type="text" name="productName" value="${param.productName}" id="tensp">
                                    <div class="error-message" id="tensp-error"></div>
                                </div>
                                 <div class="form-group col-md-4">
                                    <label class="control-label">Code</label>
                                    <input class="form-control" type="text" name="productCode" id="code">
                                    <div class="error-message" id="code-error"></div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Số lượng</label>
                                    <input class="form-control" type="number" min="0" name="productQuantity" id="quantity">
                                    <div class="error-message" id="quantity-error"></div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="exampleSelect1" class="control-label">Danh mục</label>
                                    <select class="form-control" id="exampleSelect1" name="productCategory" required>
                                        <option disabled >-- Chọn danh mục --</option>
                                        <c:forEach items="${requestScope.list}" var="i">
                                            <option value="${i.id}">${i.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="exampleSelect1" class="control-label">Nguyên liệu</label>
                                    <select class="form-control" id="exampleSelect2" name="productMaterial" required>
                                        <option disabled >-- Chọn nguyên liệu --</option>
                                        <c:forEach items="${requestScope.listMaterial}" var="i">
                                            <option value="${i.id}">${i.original}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Giá bán</label>
                                    <input class="form-control" type="number" name="productPrice" id="price">
                                    <div class="error-message" id="price-error"></div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Size</label>
                                    <input class="form-control" type="text" name="productSize" id="size">
                                    <div class="error-message" id="size-error"></div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Discount</label>
                                    <input class="form-control" type="number" step="0.01" name="productDiscount" id="discount">
                                    <div class="error-message" id="discount-error"></div>
                                </div>
                                
                                <div class="form-group col-md-12">
                                    <label class="control-label">Ảnh sản phẩm</label>
                                    <div id="myfileupload">
                                        <input type="file" id="uploadfile" name="ImageUpload" onchange="readURL(this);">
                                    </div>
                                    <div id="thumbbox">
                                        <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none">
                                        <a class="removeimg" href="javascript:"></a>
                                    </div>
                                    <div id="boxchoice">
                                        <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                                        <p style="clear:both"></p>
                                    </div>
                                    <span id="imageError" class="error"></span>
                                </div>
                            </form>
                        </div>
                        <button class="btn btn-save" type="button" onclick="validateForm()">Lưu lại</button>
                        <a class="btn btn-cancel" href="ManagerProductController">Hủy bỏ</a>
                    </div>



                    </main>
                    <!--
                    MODAL DANH MỤC
                    -->

                    <div class="modal fade" id="adddanhmuc" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
                         data-backdrop="static" data-keyboard="false">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">

                                <div class="modal-body" style="width: fit-content;">
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
                                        <a class="btn btn-cancel" data-dismiss="modal" href="ManagerProductController">Hủy bỏ</a>



                                    </form>
                                </div>
                                <div class="modal-footer">
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!--
                    MODAL Nguyên liệu
                    -->

                    <div class="modal fade" id="addmaterial" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
                         data-backdrop="static" data-keyboard="false">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">

                                <div class="modal-body" style="width: fit-content;">
                                    <form action="createMaterial" method="post" >
                                        <div class="row">
                                            <div class="form-group  col-md-12">
                                                <span class="thong-tin-thanh-toan">
                                                    <h5>Thêm mới nguyên liệu </h5>
                                                </span>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label class="control-label">Nhập tên nguyên liệu mới</label>
                                                <input class="form-control" type="text" required name="material">
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label class="control-label">Những nguyên liệu hiện đang có</label>
                                                <ul class="compact-list">
                                                    <c:forEach items="${requestScope.listMaterial}" var="i" varStatus="status">
                                                        <div>${status.index + 1}. ${i.original}</div>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>


                                        <button class="btn btn-save" type="submit">Lưu lại</button>
                                        <a class="btn btn-cancel" data-dismiss="modal" href="ManagerProductController">Hủy bỏ</a>



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
                        function isDecimal(number) {
                            // Convert the number to a string
                            var numberAsString = number.toString();

                            // Check if the string representation contains a decimal point
                            return numberAsString.includes(".");
                        }
                        function validateForm() {
                            var tensp = document.getElementById("tensp").value.trim();
                            var quantity = document.getElementById("quantity").value.trim();
                            var price = document.getElementById("price").value.trim();
                            var discount = document.getElementById("discount").value.trim();
                            var code = document.getElementById("code").value.trim();
                            var size = document.getElementById("size").value.trim();
                            
                            var imageError = document.getElementById("imageError");

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
                            } else if(quantity <=0){
                                 document.getElementById("quantity").classList.add("error-input");
                                document.getElementById("quantity-error").textContent = "Số lượng vui lòng lớn hơn 0.";
                                document.getElementById("quantity-error").style.color = "red";
                                document.getElementById("quantity").style.borderColor = "red";
                                isValid = false;
                                
                            } else if(isDecimal(quantity){
                                 document.getElementById("quantity").classList.add("error-input");
                                document.getElementById("quantity-error").textContent = "Số lượng không thể là số thập phân.";
                                document.getElementById("quantity-error").style.color = "red";
                                document.getElementById("quantity").style.borderColor = "red";
                                isValid = false;
                                
                            }
                            else {
                                document.getElementById("quantity").classList.remove("error-input");
                                document.getElementById("quantity-error").textContent = "";
                                document.getElementById("quantity-error").style.color = "";
                                 document.getElementById("quantity").style.borderColor = "";
                            }

                            if (code === "") {
                                document.getElementById("code").classList.add("error-input");
                                document.getElementById("code-error").textContent = "Vui lòng nhập mã sản phẩm.";
                                document.getElementById("code-error").style.color = "red";
                                document.getElementById("code").style.borderColor = "red";
                                isValid = false;
                            } 
                            else {
                                document.getElementById("code").classList.remove("error-input");
                                document.getElementById("code-error").textContent = "";
                                document.getElementById("code-error").style.color = "";
                                 document.getElementById("code").style.borderColor = "";
                            }
                            
                            if (size === "") {
                                document.getElementById("size").classList.add("error-input");
                                document.getElementById("size-error").textContent = "Vui lòng nhập hình dáng và kích cỡ.";
                                document.getElementById("size-error").style.color = "red";
                                document.getElementById("size").style.borderColor = "red";
                                isValid = false;
                            } 
                            else {
                                document.getElementById("size").classList.remove("error-input");
                                document.getElementById("size-error").textContent = "";
                                document.getElementById("size-error").style.color = "";
                                 document.getElementById("size").style.borderColor = "";
                            }


                            if (price === "") {
                                document.getElementById("price").classList.add("error-input");
                                document.getElementById("price-error").textContent = "Vui lòng nhập giá bán.";
                                document.getElementById("price-error").style.color = "red";
                                document.getElementById("price").style.borderColor = "red";
                                isValid = false;
                            } else if(price <= 0)
                            {
                                 document.getElementById("price").classList.add("error-input");
                                document.getElementById("price-error").textContent = "Giá bán lớn hơn 0.";
                                document.getElementById("price-error").style.color = "red";
                                document.getElementById("price").style.borderColor = "red";
                                isValid = false;
                                
                                
                            }
                            else {
                                document.getElementById("price").classList.remove("error-input");
                                document.getElementById("price-error").textContent = "";
                                document.getElementById("price-error").style.color = "";
                                document.getElementById("price").style.borderColor = "";
                            }

                            if (discount === "") {
                                document.getElementById("discount").classList.add("error-input");
                                document.getElementById("discount-error").textContent = "Vui lòng nhập discount.";
                                document.getElementById("discount-error").style.color = "red";
                                document.getElementById("discount").style.borderColor = "red";
                                isValid = false;
                            } else if (discount < 0 || discount > 100){
                                document.getElementById("discount").classList.add("error-input");
                                document.getElementById("discount-error").textContent = " Discount vui lòng lớn hơn 0 và nhỏ hơn 100.";
                                document.getElementById("discount-error").style.color = "red";
                                document.getElementById("discount").style.borderColor = "red";
                                isValid = false;
                                
                            }
                                
                            else {
                                document.getElementById("discount").classList.remove("error-input");
                                document.getElementById("discount-error").textContent = "";
                                document.getElementById("discount-error").style.color = "";
                                  document.getElementById("discount").style.borderColor = "";
                            }

                            // Validate the image field
                            var fileInput = document.getElementById("uploadfile");
                            var file = fileInput.files[0];
                            if (!file) {
                                document.getElementById("uploadfile").classList.add("error-input");
                                imageError.textContent = "Vui lòng chọn ảnh.";
                                imageError.style.color = "red";
                                isValid = false;
                            } else {
                                document.getElementById("uploadfile").classList.remove("error-input");
                                imageError.textContent = "";
                                imageError.style.color = "";
                            }

                            if (isValid) {
                                document.getElementById("createPro").submit();
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

