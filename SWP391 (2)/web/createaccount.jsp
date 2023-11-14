<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Thêm sản phẩm | Quản trị Admin</title>
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
         <jsp:include page="adminPage.jsp"/>
         <main class="app-content">
             <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="ManagerAllAccount">danh sách nhân viên</a></li>
                    <li class="breadcrumb-item"><a href="CreateAccount">Thêm nhân viên</a></li>
                </ul>
             </div> 
             
             <div class="row">
                 <div class="col-md-12">
                     <div class="tile">
                         <h3 class="tile-title">Tạo mới nhân viên</h3>
                         <div class="tile-body">
                             <form class="row" action="CreateAccount" method="post" enctype="multipart/form-data" id="createPro">
                                <div class="form-group col-md-4">
                                    <label class="control-label">Tên đầu</label>
                                    <input class="form-control" type="text"  name="firstname" id="name">
                                    <span id="nameError" class="error"></span>
                                </div>
                                
                                <div class="form-group col-md-4">
                                    <label class="control-label">Tên cuối</label>
                                    <input class="form-control" type="text"  name="lastname" id="lastname">
                                    <span id="lastnameError" class="error"></span>
                                </div>
                                 
                                  <div class="form-group col-md-4">
                                    <label class="control-label">Địa chỉ</label>
                                    <input class="form-control" type="text"  name="address" id="address">
                                    <span id="addressError" class="error"></span>
                                </div>
                                 
                                  <div class="form-group col-md-4">
                                    <label class="control-label">Ngày sinh</label>
                                    <input class="form-control" type="date" name="birthday" id="birthday" required>
                                    <span id="birthdayError" class="error"></span>
                                </div>
                                 
                                  <div class="form-group col-md-3">
                                    <label for="exampleSelect1" class="control-label">Giới tính</label>
                                    <select class="form-control" id="exampleSelect1" name="gender" required>
                                        <option disabled >-- Giới tính --</option>
                                        <option value="true">Nam</option>
                                        <option value="false">Nữ</option>
                                    </select>
                                </div>
                                 
                                 <div class="form-group col-md-3">
                                    <label for="exampleSelect1" class="control-label">Chức vụ</label>
                                    <select class="form-control" id="exampleSelect1" name="role" required>
                                        <option disabled >-- Chọn chức vụ --</option>
                                         <c:forEach items="${requestScope.list}" var="user">
                                            <option value="${user.roleid}">${user.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                 
                                   <div class="form-group col-md-4">
                                    <label class="control-label">Email</label>
                                    <input class="form-control" type="text" name="email" id="username" required>
                                    <span id="usernameError" class="error"></span>
                                </div>
                                 
                                 <div class="form-group col-md-4">
                                    <label class="control-label">Mật khẩu</label>
                                    <input class="form-control" type="text" name="password" id="pass" required>
                                    <span id="passError" class="error"></span>
                                </div>
                                 
                                <div class="form-group col-md-4">
                                    <label class="control-label">Số điện thoại</label>
                                    <input class="form-control" type="text" name="phone" id="phone" required>
                                    <span id="phoneError" class="error"></span>
                                </div>
                                 
                                 <div class="form-group col-md-12">
                                    <label class="control-label">Ảnh nhân viên</label>
                                    <div id="myfileupload">
                                        <input required type="file" id="uploadfile" name="ImageUpload" onchange="readURL(this);">
                                    </div>
                                    <div id="thumbbox">
                                        <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
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
                        <a class="btn btn-cancel" href="ManagerAllAccount">Hủy bỏ</a>
                     </div>
                 </div>
             </div>
         </main>
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
    var firstname = document.getElementById("name").value.trim();
    var lastname = document.getElementById("lastname").value.trim();
    var address = document.getElementById("address").value.trim();
    var birthday = document.getElementById("birthday").value;
    var pass = document.getElementById("pass").value.trim();
    var username = document.getElementById("username").value.trim();
    var phone = document.getElementById("phone").value.trim();
    
    var imageError = document.getElementById("imageError");
    var nameError = document.getElementById("nameError");
    var lastnameError = document.getElementById("lastnameError");
    var addressError = document.getElementById("addressError");
    var usernameError = document.getElementById("usernameError");
    var passError = document.getElementById("passError");
    var birthdayError = document.getElementById("birthdayError");
    var phoneError = document.getElementById("phoneError");

    var fileInput = document.getElementById("uploadfile");
    var file = fileInput.files[0];

    var isValid = true;

    if (firstname === "") {
        nameError.textContent = "Vui lòng nhập họ và tên.";
        nameError.style.color = "red";
        document.getElementById("name").style.borderColor = "red";
        isValid = false;
    } else if (firstname.length > 25) {
        nameError.textContent = "Tên không quá 25 ký tự.";
        nameError.style.color = "red";
        document.getElementById("name").style.borderColor = "red";
        isValid = false;
    } else {
        nameError.textContent = "";
        nameError.style.color = "";
        document.getElementById("name").style.borderColor = "";
    }

    if (lastname === "") {
        lastnameError.textContent = "Vui lòng nhập họ và tên.";
        lastnameError.style.color = "red";
        document.getElementById("lastname").style.borderColor = "red";
        isValid = false;
    } else if (lastname.length > 25) {
        lastnameError.textContent = "Tên không quá 25 ký tự.";
        lastnameError.style.color = "red";
        document.getElementById("lastname").style.borderColor = "red";
        isValid = false;
    } else {
        lastnameError.textContent = "";
        lastnameError.style.color = "";
        document.getElementById("lastname").style.borderColor = "";
    }

    if (address === "") {
        addressError.textContent = "Vui lòng nhập địa chỉ.";
        addressError.style.color = "red";
        document.getElementById("address").style.borderColor = "red";
        isValid = false;
    } else if (address.length > 15) {
        addressError.textContent = "Địa chỉ không quá 15 ký tự.";
        addressError.style.color = "red";
        document.getElementById("address").style.borderColor = "red";
        isValid = false;
    } else {
        addressError.textContent = "";
        addressError.style.color = "";
        document.getElementById("address").style.borderColor = "";
    }
   

//    if (phone === "") {
//        phoneError.textContent = "Vui lòng nhập số điện thoại.";
//        phoneError.style.color = "red";
//        document.getElementById("phone").style.borderColor = "red";
//        isValid = false;
//    } else if (phone.length < 10 || phone.length > 11) {
//        phoneError.textContent = "Số điện thoại phải có từ 10 đến 11 số.";
//        phoneError.style.color = "red";
//        document.getElementById("phone").style.borderColor = "red";
//        isValid = false;
//    } else {
//        phoneError.textContent = "";
//        phoneError.style.color = "";
//        document.getElementById("phone").style.borderColor = "";
//    }

    if (birthday === "") {
        birthdayError.textContent = "Vui lòng chọn ngày sinh";
        birthdayError.style.color = "red";
        document.getElementById("birthday").style.borderColor = "red";
        isValid = false;
    } else {
        birthdayError.textContent = "";
        birthdayError.style.color = "";
        document.getElementById("birthday").style.borderColor = "";
    }

    if (pass === "") {
        passError.textContent = "Vui lòng nhập mật khẩu.";
        passError.style.color = "red";
        document.getElementById("pass").style.borderColor = "red";
        isValid = false;
    } else if (pass.length < 3 || pass.length > 100) {
        passError.textContent = "Mật khẩu kém.";
        passError.style.color = "red";
        document.getElementById("pass").style.borderColor = "red";
        isValid = false;
    } else if (/[^a-zA-Z0-9]/.test(pass)) {
        passError.textContent = "Mật khẩu không được chứa ký tự đặt biệt.";
        passError.style.color = "red";
        document.getElementById("pass").style.borderColor = "red";
        isValid = false;
    } else {
        passError.textContent = "";
        passError.style.color = "";
        document.getElementById("pass").style.borderColor = "";
    }
   
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'LoadEmail?email=' + username, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
              if (username === "") {
         usernameError.textContent = "Vui lòng nhập tên đăng nhập.";
         usernameError.style.color = "red";
         document.getElementById("username").style.borderColor = "red";
         isValid = false;
                }else if (!username.endsWith("@gmail.com")) {
        usernameError.textContent = "Email phải kết thúc bằng '@gmail.com'.";
        usernameError.style.color = "red";
        document.getElementById("username").style.borderColor = "red";
         isValid = false;
                } else if (username.length < 10 || username.length > 100) {
          usernameError.textContent = "Địa chỉ không đúng định dạng.";
           usernameError.style.color = "red";
          document.getElementById("username").style.borderColor = "red";
          isValid = false;
                }
           else if (response.available) {
                usernameError.textContent = "";
                usernameError.style.color = "";
                document.getElementById("username").style.borderColor = "";
                document.getElementById("createPro").submit();
            } else {
                usernameError.textContent = "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.";
                usernameError.style.color = "red";
                document.getElementById("username").style.borderColor = "red";
            }
        }
    };
    xhr.send();

     var xhrr = new XMLHttpRequest();
    xhrr.open('GET', 'LoadPhone?phone=' + phone, true);
    xhrr.onreadystatechange = function () {
        if (xhrr.readyState === 4 && xhrr.status === 200) {
            var response = JSON.parse(xhrr.responseText);
              if (username === "") {
         phoneError.textContent = "Vui lòng nhập số điện thoại.";
         phoneError.style.color = "red";
         document.getElementById("phone").style.borderColor = "red";
         isValid = false;
             } else if (phone.length > 10 || phone.length < 11) {
          phoneError.textContent = "Số điện thoại không đúng định dạng.";
          phoneError.style.color = "red";
          document.getElementById("phone").style.borderColor = "red";
          isValid = false;
                }
           else if (response.available) {
                phoneError.textContent = "";
                phoneError.style.color = "";
                document.getElementById("phone").style.borderColor = "";
                document.getElementById("createPro").submit();
            } else {
                usernameError.textContent = "số điện thoại đã tồn tại. Vui lòng chọn tên khác.";
                usernameError.style.color = "red";
                document.getElementById("phone").style.borderColor = "red";
            }
        }
    };
    xhrr.send();
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

        
        
        <script>////
////            <% if (request.getAttribute("message") != null) {%>
            swal("<%= request.getAttribute("message")%>", "", "success");
            <% request.removeAttribute("message"); %>
            <% }%>
        </script>

    </body>
    
    
</html>