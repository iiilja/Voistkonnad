<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Change voistkond</title>
    </head>
    <body>

        <a href='../voistkonnad/s'>Voiskonnad</a> | <a href='../voistkonnad/new'>Create new</a> <br>
        <p color="red">${error}</p>
        <form action='s?action=update' method=POST>
            <table bgcolor='#000000' border=0 cellpadding=0 cellspacing=0>
                <tr>
                    <td>
                        <table width=100% border=0 cellpadding=2 cellspacing=1>
                            <TR BGCOLOR='#ffffff'><td BGCOLOR='#cccccc' nowrap>name</td><td>&nbsp;<b><font color='#0000ff'><input type='text' value='${voistkond.name}' name='name'></font></b></TD></tr>
                            <TR BGCOLOR='#ffffff'><td BGCOLOR='#cccccc' nowrap>sport</td>
                                <td>&nbsp;<b><font color='#0000ff'>
                                        <select name="sportCode">
                                            <c:forEach var="spordiala" items="${spordialas}" >
                                                <option value="${spordiala.sportCode}" ${spordiala.selected ? "selected": ""}>${spordiala.name}</option>
                                            </c:forEach>
                                        </select>
                                        </font></b></TD></tr>
                            <TR BGCOLOR='#ffffff'>
                                <td BGCOLOR='#cccccc' nowrap>country</td>
                                <td>&nbsp;<b><font color='#0000ff'>
                                        <select name="riikKood">
                                            <c:forEach var="riik" items="${riiks}" >
                                                <option value="${riik.riikKood}" ${riik.selected ? "selected": ""}>${riik.name}</option>
                                            </c:forEach>
                                        </select>
                                        </font></b>
                                </TD></tr>
                            <TR BGCOLOR='#ffffff'><td BGCOLOR='#cccccc' nowrap>state</td>
                                <td>&nbsp;<b><font color='#0000ff'>
                                        <select name="seisundiLiik">
                                            <c:forEach var="seisundiLiik" items="${seisundiLiiks}" >
                                                <option value="${seisundiLiik.voistkonnaSeisundiLiikKood}" ${seisundiLiik.selected ? "selected": ""}>${seisundiLiik.name}</option>
                                            </c:forEach>
                                        </select>
                                        </font></b>
                                </TD></tr>
                            <TR BGCOLOR='#ffffff'><td BGCOLOR='#cccccc' nowrap>email</td><td>&nbsp;<b><font color='#0000ff'><input type='text' value='${voistkond.getEMail()}' name='email'></font></b></TD></tr>
                            <TR BGCOLOR='#ffffff'><td BGCOLOR='#cccccc' nowrap>description</td><td>&nbsp;<b><font color='#0000ff'><textarea name='description' cols=25 rows=6>${voistkond.description}</textarea></font></b></TD></tr>
                        </table>
                    </td>
                </tr>
            </table>

            <input type="submit" value="salvesta" >
            <input type="hidden" name="id" value="${voistkond.teamId}" />
        </form>




    </body>
</html>