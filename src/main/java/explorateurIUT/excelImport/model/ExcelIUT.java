/*
 * Copyright (C) 2023 IUT Laval - Le Mans Université.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package explorateurIUT.excelImport.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Remi Venant
 */
public class ExcelIUT {

    private static final Log LOG = LogFactory.getLog(ExcelIUT.class);

    private final String nom;
    private String site;
    private final List<ExcelDepartement> departements = new ArrayList<>();
    private String region;
    private String tel;
    private String adresse;
    private String contact;
    private String mel;
    private String url;
    private Double coorGpsLat;
    private Double coorGpsLon;

    public ExcelIUT(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        if (site == null) {
            LOG.warn("Setting null ville: do nothing");
            return;
        }
        if (this.site != null) {
            LOG.warn("Overiding ville. old: " + this.site + " | new: " + site);
        }
        this.site = site;
    }

    public List<ExcelDepartement> getDepartements() {
        return departements;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region == null) {
            LOG.warn("Setting null region: do nothing");
            return;
        }
        if (this.region != null) {
            LOG.warn("Overiding region. old: " + this.region + " | new: " + region);
        }
        this.region = region;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        if (tel == null) {
            LOG.warn("Setting null tel: do nothing");
            return;
        }
        if (this.tel != null) {
            LOG.warn("Overriding tel. Old: " + this.tel + " | new: " + tel);
        }
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        if (adresse == null) {
            LOG.warn("Setting null adresse: do nothing");
            return;
        }
        if (this.adresse != null) {
            LOG.warn("Overriding adresse. Old: " + this.adresse + " | new: " + adresse);
        }
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (contact == null) {
            LOG.warn("Setting null contact: do nothing");
            return;
        }
        if (this.contact != null) {
            LOG.warn("Overriding contact. Old: " + this.contact + " | New: " + contact);
        }
        this.contact = contact;
    }

    public String getMel() {
        return mel;
    }

    public void setMel(String mel) {
        if (mel == null) {
            LOG.warn("Setting null mel: do nothing");
            return;
        }
        if (this.mel != null) {
            LOG.warn("Overriding mel. Old: " + this.mel + " | New: " + mel);
        }
        this.mel = mel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url == null) {
            LOG.warn("Setting null url: do nothing");
            return;
        }
        if (this.url != null) {
            LOG.warn("Overriding url. Old: " + this.url + " | New: " + url);
        }
        this.url = url;
    }

    public Double getCoorGpsLat() {
        return coorGpsLat;
    }

    public void setCoorGpsLat(Double coorGpsLat) {
        if (coorGpsLat == null) {
            LOG.warn("Setting null coorGpsLat: do nothing");
            return;
        }
        if (this.coorGpsLat != null) {
            LOG.warn("Overriding coorGpsLat. Old: " + this.coorGpsLat + " | new: " + coorGpsLat);
        }
        this.coorGpsLat = coorGpsLat;
    }

    public Double getCoorGpsLon() {
        return coorGpsLon;
    }

    public void setCoorGpsLon(Double coorGpsLon) {
        if (coorGpsLon == null) {
            LOG.warn("Setting null coorGpsLon: do nothing");
            return;
        }
        if (this.coorGpsLon != null) {
            LOG.warn("Overriding coorGpsLon. Old: " + this.coorGpsLon + " | new: " + coorGpsLon);
        }
        this.coorGpsLon = coorGpsLon;
    }

    public void format(StringBuilder sb, String padding, int nbPads) {
        String pad = padding.repeat(nbPads);
        sb.append(pad).append("IUT ").append(nom).append(System.lineSeparator());
        sb.append(pad).append("- ville : ").append(site).append(System.lineSeparator());
        sb.append(pad).append("- tel : ").append(tel).append(System.lineSeparator());
        sb.append(pad).append("- adresse : ").append(adresse).append(System.lineSeparator());
        sb.append(pad).append("- url : ").append(url).append(System.lineSeparator());
        sb.append(pad).append("- gps : ").append('[').append(coorGpsLat).append(", ").append(coorGpsLon).append(']').append(System.lineSeparator());
        sb.append(pad).append("- departements : ").append(System.lineSeparator());
        for (ExcelDepartement dept : this.departements) {
            dept.format(sb, padding, nbPads + 1);
        }
    }

}
