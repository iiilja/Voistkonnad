<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Voistkonnad</title>
    </head>
    <body>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <a href='../voistkonnad/s'>Voiskonnad</a> | <a href='../voistkonnad/new'>Create new</a> | <a href='../user/logout'>Logi valja</a><br>
        voistkonds list: 
        <br>
        <table border=1 cellpadding=2 cellspacing=1>
            <tr bgcolor='#cccccc'>
                <td><STRONG> voistkondId&nbsp;</td>
                <td><STRONG>nimetus&nbsp;</td>
                <td><STRONG>spordialaNimetus&nbsp;</td>
                <td><STRONG>riikNimetus&nbsp;</td>
                <td><STRONG>voistkonnaSeisundiLiik&nbsp;</td>
                <td><STRONG>getEMail&nbsp;</td>
                <td><STRONG>regAeg&nbsp;</td>
                <td><STRONG>kirjeldus&nbsp;</td>
                <td><STRONG>muutja&nbsp;</td>
                <td>link</td>
            </tr>
            <tr></tr>
            <c:forEach var="voistkond" items="${voistkonds}" >
                <TR BGCOLOR='#FFFFFF' ><TD  nowrap>
                        &nbsp${voistkond.voistkondId}&nbsp;</TD>
                    <TD>${voistkond.nimetus}&nbsp</TD>
                    <TD>${voistkond.spordialaNimetus}&nbsp</TD>
                    <TD>${voistkond.riikNimetus}&nbsp</TD>
                    <TD>${voistkond.voistkonnaSeisundiLiik}&nbsp</TD>
                    <TD>${voistkond.getEMail()}&nbsp</TD>
                    <TD>${voistkond.regAeg}&nbsp</TD>
                    <TD>${voistkond.kirjeldus}&nbsp</TD>
                    <TD>${voistkond.muutja}&nbsp</TD>
                    <TD align='top' nowrap>
                        <a HREF='s?id=${voistkond.voistkondId}' TARGET='_self'><b><u>change</u><b></a>
                    </TD>
                </TR>
            </c:forEach>
        </table>

        <br>
        <br>
    </body>
</html>