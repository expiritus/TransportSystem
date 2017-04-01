package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
import com.belhard.misha.dao.impl.DaoTransport;
import com.belhard.misha.dao.impl.DaoTransportType;
import com.belhard.misha.entity.Transport;
import com.belhard.misha.entity.TransportType;
import com.belhard.misha.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/admin/transport")
public class ShowTransport extends HttpServlet {

    public static final String URL = "/admin/transport";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoTransport daoTransport = (DaoTransport) DaoFactory.getDao(DaoTypes.Transport);

        DaoTransportType daoTransportType = (DaoTransportType) DaoFactory.getDao(DaoTypes.TransportType);

        List<Transport> transports = daoTransport.findAll(Transport.class);
        List<TransportType> transportTypes = daoTransportType.findAll(TransportType.class);

        req.setAttribute("transports", transports);
        req.setAttribute("transportTypes", transportTypes);
        HttpUtils.forward(req, resp, "ShowTransport", "/WEB-INF/pages/admin/categories/show/transport.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String addTransport = req.getParameter("addTransport");
        String deleteTransport = req.getParameter("deleteTransport");

        DaoTransport daoTransport = (DaoTransport) DaoFactory.getDao(DaoTypes.Transport);
        if (addTransport != null) {
            String model = req.getParameter("model");
            String capacity = req.getParameter("capacity");
            String speed = req.getParameter("speed");
            int transportTypeId = Integer.parseInt(req.getParameter("transportType"));


            Map<String, String> errorMap = validateData(model, capacity, speed);
            if (errorMap.size() > 0) {
                Map<String, String> stateFillMap = stateFull(model, capacity, speed);
                HttpUtils.setSessionAttribute(req,"errorMap", errorMap);
                HttpUtils.setSessionAttribute(req,"stateFullMap", stateFillMap);
                HttpUtils.redirect(resp, ShowTransport.URL);
                return;
            }

            HttpUtils.invalidateSessionByAttribute(req, "errorMap");
            HttpUtils.invalidateSessionByAttribute(req, "stateFullMap");

            Transport transport = new Transport();
            transport.setTransportTypeId(transportTypeId);
            transport.setModel(model);
            transport.setCapacity(Integer.parseInt(capacity));
            transport.setSpeed(Double.parseDouble(speed));
            daoTransport.insert(transport);
        } else if (deleteTransport != null) {
            int transportId = Integer.parseInt(req.getParameter("deleteTransport"));
            daoTransport.delete(Transport.class, transportId);
        }

        HttpUtils.redirect(resp, req.getContextPath() + ShowTransport.URL);
    }

    public static Map<String, String> validateData(String model, String capacity, String speed) {
        Map<String, String> errorMap = new HashMap<>();
        if (StringUtils.isBlank(model) ||StringUtils.isEmpty(model)) {
            errorMap.put("errorModel", "Поле модель не может быть пустым");
        }

        if(StringUtils.isEmpty(capacity) || StringUtils.isBlank(capacity)){
            errorMap.put("errorCapacity", "Поле вместимость не может быть пустым");
        }else {
            if(!StringUtils.isNumeric(capacity)){
                errorMap.put("errorCapacity", "Вместимость может быть только числом");
            }
        }


        if(StringUtils.isEmpty(speed) || StringUtils.isBlank(speed)){
            errorMap.put("errorSpeed", "Поле скорость не может быть пустым");
        }else {
            if(!StringUtils.isNumeric(speed)){
                errorMap.put("errorSpeed", "Скорость может быть только числом");
            }
        }

        return errorMap;

    }

    public static Map<String, String> stateFull(String model, String capacity, String speed){
        Map<String, String> stateFullMap = new HashMap<>();
        stateFullMap.put("stateFullModel", model);
        stateFullMap.put("stateFullCapacity", capacity);
        stateFullMap.put("stateFullSpeed", speed);
        return stateFullMap;
    }
}
