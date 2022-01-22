<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   
    <%
    for(int i=0;i<3;i++)
    {
     out.println("<c:url value=\""+request.getAttribute("imgName"+(i+1))+" \" var=\"photo\"/>");
    }
     %>
</head>
<body class="bg-danger">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand active" href="/homepage/display">Home</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
             
              <li class="nav-item">
                <a class="nav-link" href="/cart/show">Shopping cart</a>
              </li>
              
            </ul>
            
          </div>
        </div>
      </nav>
          
     <div class="container bg-info">
         <div class="row">
      
          <%
      int i;
      for(i=0;i<(int)request.getAttribute("noOfProducts");i++)
      {
      out.println("<div class=\"col\">"
        + "<div class=\"card m-5 border-dark\" style=\"width: 18rem;\">\n"
        + "        <img src=\""+ request.getAttribute("imgName"+(i+1))+"\" class=\"card-img-top w-50 m-auto\" />\n"
        + "        <div class=\"card-body text-center\">\n"
        + "          <h5 class=\"card-title\">"+request.getAttribute("pname"+(i+1))+"</h5>\n"
        + "          <p class=\"card-text\">"+request.getAttribute("pdesp"+(i+1))+"<br>"+request.getAttribute("price"+(i+1))+"Rs</p>\n"
        + "           <input type=\"number\" name=\"qty\" id=\"q" +request.getAttribute("pid"+(i+1)) +"\" value=\"0\">\n"
        + "          <button onclick=\"getData(this); \" class=\"btn btn-primary my-2\" id=\"" + request.getAttribute("pid"+(i+1)) + "\">Add to Cart</button>\n"
        + "        </div>\n"
        + "      </div>"
        + "      </div>");
      }
        %>
    </div>
    </div>       

    <script src="../../product.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>





