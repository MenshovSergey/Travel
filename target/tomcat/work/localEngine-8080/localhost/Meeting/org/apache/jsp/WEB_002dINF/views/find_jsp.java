package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class find_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Remove Markers</title>\n");
      out.write("    <style>\n");
      out.write("      html, body {\n");
      out.write("        height: 100%;\n");
      out.write("        margin: 0;\n");
      out.write("        padding: 0;\n");
      out.write("      }\n");
      out.write("      #map {\n");
      out.write("        height: 100%;\n");
      out.write("      }\n");
      out.write("#floating-panel {\n");
      out.write("    position: absolute;\n");
      out.write("    top: 10px;\n");
      out.write("    left: 25%;\n");
      out.write("    z-index: 5;\n");
      out.write("    background-color: #fff;\n");
      out.write("    padding: 5px;\n");
      out.write("    border: 1px solid #999;\n");
      out.write("    text-align: center;\n");
      out.write("    font-family: 'Roboto','sans-serif';\n");
      out.write("    line-height: 30px;\n");
      out.write("    padding-left: 10px;\n");
      out.write("}\n");
      out.write("    </style>\n");
      out.write("\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <div id=\"floating-panel\">\n");
      out.write("      <input onclick = \"deleteMarkers();\" type = button value=\"Delete Markers\">\n");
      out.write("      <input onclick = \"saveRoute();\" type = button value = \"Save route\">\n");
      out.write("    </div>\n");
      out.write("    <div id=\"map\"></div>\n");
      out.write("    <p>Click on the map to add markers.</p>\n");
      out.write("    <script>\n");
      out.write("var map;\n");
      out.write("var markers = [];\n");
      out.write("var allPoints = [];\n");
      out.write("\n");
      out.write("function Point() {\n");
      out.write("    var lat;\n");
      out.write("    var lng;\n");
      out.write("    this.setLatitude = function (latitude) {\n");
      out.write("        this.lat = latitude;\n");
      out.write("    }\n");
      out.write("    this.setLongitude = function (long) {\n");
      out.write("         this.lng = long;\n");
      out.write("    }\n");
      out.write("}\n");
      out.write("\n");
      out.write("function initMap() {\n");
      out.write("    var position = navigator.geolocation.getCurrentPosition(function(position) {\n");
      out.write("        var latitude = position.coords.latitude;\n");
      out.write("        var longitude = position.coords.longitude\n");
      out.write("        map = new google.maps.Map(document.getElementById('map'), {\n");
      out.write("            center: {lat: latitude, lng: longitude},\n");
      out.write("            zoom: 12\n");
      out.write("        });\n");
      out.write("        map.addListener('click', function(event) {\n");
      out.write("            addMarker(event.latLng);\n");
      out.write("        });\n");
      out.write("    });\n");
      out.write("}\n");
      out.write("\n");
      out.write("function addMarker(location) {\n");
      out.write("    var marker = new google.maps.Marker({\n");
      out.write("        position: location,\n");
      out.write("        map: map\n");
      out.write("    });\n");
      out.write("    markers.push(marker);\n");
      out.write("    var p = new Point();\n");
      out.write("    p.setLatitude(location.lat());\n");
      out.write("    p.setLongitude(location.lng());\n");
      out.write("    allPoints.push(p);\n");
      out.write("}\n");
      out.write("\n");
      out.write("function deleteMarkers() {\n");
      out.write("    if (markers.length > 0) {\n");
      out.write("        var last = markers.pop();\n");
      out.write("        last.setMap(null);\n");
      out.write("        allPoints.pop();\n");
      out.write("    }\n");
      out.write("}\n");
      out.write("\n");
      out.write("function saveRoute() {\n");
      out.write("    var myUrl = \"http://localhost:8080/Meeting/save\";\n");
      out.write("    var property = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userForm.sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(";\n");
      out.write("    //alert(property);\n");
      out.write("    if (allPoints.length > 0) {\n");
      out.write("        var json = JSON.stringify(allPoints);\n");
      out.write("        //document.write(json);\n");
      out.write("        jQuery.ajax({\n");
      out.write("                headers: {\n");
      out.write("                        'Accept': 'application/json',\n");
      out.write("                        'Content-Type': 'application/json'\n");
      out.write("                    },\n");
      out.write("                url: myUrl,\n");
      out.write("                type: 'POST',\n");
      out.write("                data: json,\n");
      out.write("                dataType: 'json',\n");
      out.write("               success:function(result)//we got the response\n");
      out.write("               {\n");
      out.write("                alert('Successfully called');\n");
      out.write("               },\n");
      out.write("               error:function(exception){alert('Exeption:'+exception);}\n");
      out.write("\n");
      out.write("\n");
      out.write("            })\n");
      out.write("    }\n");
      out.write("}\n");
      out.write("function resultSave (data, textStatus, jqXHR) {\n");
      out.write("    alert(textStatus);\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>\n");
      out.write("    <script async defer\n");
      out.write("         src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyB2Xr6t-W21QxCKzhDJL1Zwz19pNGnc78I&signed_in=true&callback=initMap\"></script>\n");
      out.write("  </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
