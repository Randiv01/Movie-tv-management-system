<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="adminmodel.Admin" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    HttpSession sessionObj = request.getSession(false);

    Admin loggedInAdmin = null;
    if (sessionObj != null) {
        loggedInAdmin = (Admin) sessionObj.getAttribute("admin");
    }

    if (loggedInAdmin == null) {
        response.sendRedirect("Login.jsp");  // Redirect if not logged in
        return;
    }

    // Optional: Get admin ID or other info if needed
    int adminId = loggedInAdmin.getAid();
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Admin - MovieHub</title>
    <link rel="stylesheet" href="CSS/Manageadmin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <%@ include file="AdminHeader.jsp" %>

    <div class="admin-user-management">
        <div class="page-title">
            <h2><i class="fas fa-user-shield"></i> Admin Management Portal</h2>
        </div>

        <h3>All Registered Admins</h3>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Mobile</th>
                    <th>Address</th>
                    <th>NIC</th>
                    <th>Admin Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="admin" items="${admins}">
				    <tr>
				        <td><input form="form${admin.aid}" type="text" name="aid" value="${admin.aid}" readonly /></td>
				        <td><input form="form${admin.aid}" type="text" name="afullname" value="${admin.afullname}" /></td>
				        <td><input form="form${admin.aid}" type="email" name="agmail" value="${admin.gmail}" /></td>
				        <td><input form="form${admin.aid}" type="text" name="apassword" value="${admin.apassword}" /></td>
				        <td>
				            <select form="form${admin.aid}" name="agender" class="ma-form-group" required>
				                <option value="Male" <c:if test="${admin.gender == 'Male'}">selected</c:if>>Male</option>
				                <option value="Female" <c:if test="${admin.gender == 'Female'}">selected</c:if>>Female</option>
				                <option value="Other" <c:if test="${admin.gender == 'Other'}">selected</c:if>>Other</option>
				            </select>
				        </td>
				        <td><input form="form${admin.aid}" type="date" name="adob" value="${admin.dob}" /></td>
				        <td><input form="form${admin.aid}" type="text" name="amobile" value="${admin.mobile}" /></td>
				        <td><input form="form${admin.aid}" type="text" name="aaddress" value="${admin.address}" /></td>
				        <td><input form="form${admin.aid}" type="text" name="anic" value="${admin.nic}" /></td>
				        <td>
				            <select form="form${admin.aid}" name="admintype" class="ma-form-group" required>
				                <option value="Super Admin" <c:if test="${admin.admintype == 'Super Admin'}">selected</c:if>>Super Admin</option>
				                <option value="Movie Manager" <c:if test="${admin.admintype == 'Movie Manager'}">selected</c:if>>Movie Manager</option>
				                <option value="TV Series Manager" <c:if test="${admin.admintype == 'TV Series Manager'}">selected</c:if>>TV Series Manager</option>
				                <option value="Contact Support" <c:if test="${admin.admintype == 'Contact Support'}">selected</c:if>>Contact Support</option>
				                <option value="Review Manager" <c:if test="${admin.admintype == 'Review Manager'}">selected</c:if>>Review Manager</option>
				            </select>
				        </td>
				        <td>
				            <form id="form${admin.aid}" action="AllAdminUpdateServlet" method="post">
				                <button type="submit" class="action-btn edit-btn">Update</button>
				            </form>
				            <a href="AllAdminDeleteServlet?aid=${admin.aid}" class="action-btn delete-btn" onclick="return confirm('Are you sure you want to delete this user?');">
				                Delete
				            </a>
				        </td>
				    </tr>
				</c:forEach>

            </tbody>
        </table>
    </div>

    <!-- Add New Admin Section -->
    <div class="ma-container">
        <h1 class="ma-title"><i class="fas fa-user-shield"></i> Add New Admin</h1>

        <form action="manage_admins" method="post" class="ma-form">
            <div class="ma-form-row">
                <div class="ma-form-group">
                    <label for="afullname">Full Name:</label>
                    <input type="text" id="afullname" name="afullname" required>
                </div>

                <div class="ma-form-group">
                    <label for="agmail">Email:</label>
                    <input type="email" id="agmail" name="agmail" required>
                </div>
            </div>

            <div class="ma-form-row">
                <div class="ma-form-group">
                    <label for="apassword">Password:</label>
                    <input type="password" id="apassword" name="apassword" required>
                </div>
            </div>

            <div class="ma-form-row">
                <div class="ma-form-group">
                    <label for="agender">Gender:</label>
                    <select id="agender" name="agender" required>
                        <option value="">Select</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>

                <div class="ma-form-group">
                    <label for="adob">Date of Birth:</label>
                    <input type="date" id="adob" name="adob" required>
                </div>
            </div>

            <div class="ma-form-row">
                <div class="ma-form-group">
                    <label for="amobile">Mobile:</label>
                    <input type="text" id="amobile" name="amobile" required>
                </div>

                <div class="ma-form-group">
                    <label for="anic">NIC:</label>
                    <input type="text" id="anic" name="anic" required>
                </div>
            </div>

            <div class="ma-form-row">
                <div class="ma-form-group full-width">
                    <label for="aaddress">Address:</label>
                    <textarea id="aaddress" name="aaddress" rows="3" required></textarea>
                </div>
            </div>

            <div class="ma-form-row">
                <div class="ma-form-group full-width">
                    <label for="admintype">Admin Type:</label>
                    <select id="admintype" name="admintype" required>
                        <option value="">Select</option>
                        <option value="Super Admin">Super Admin</option>
                        <option value="Movie Manager">Movie Manager</option>
                        <option value="TV Series Manager">TV Series Manager</option>
                        <option value="Contact Support">Contact Support</option>
                        <option value="Review Manager">Review Manager</option>
                    </select>
                </div>
            </div>

            <div class="ma-form-actions">
                <button type="submit"><i class="fas fa-user-plus"></i> Add Admin</button>
            </div>
        </form>
    </div>

    <%@ include file="AdminFooter.jsp" %>
</body>
</html>
