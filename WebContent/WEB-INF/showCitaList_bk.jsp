<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Citas</title>
</head>
<body>
	<h1>Las citas registradas son: </h1>

	<hr>
	<c:choose>
		<c:when test="${citaList == null or citaList.isEmpty()}">
		    La lista esta vacia.
		</c:when>
		<c:otherwise>
			<table border=1>
			
				<tr>
					<th>Establecimiento</th>
					<th>Tipo Documento</th>
					<th>Num Documento</th>
					<th>Ape Paterno</th>
					<th>Consulta Solicitada</th>
				</tr>
				<c:forEach var="cita" items="${citaList}">
					<tr>
						<td>${cita.establecimiento}</td>
						<td>${cita.tipoDocumento}</td>
						<td>${cita.numDocumento}</td>
						<td>${cita.apellidoPaterno}</td>
						<td>${cita.consultaSolicitada}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<hr>
	<a href='index.html'>Pagina Inicio</a>
	<br>

</body>
</html>