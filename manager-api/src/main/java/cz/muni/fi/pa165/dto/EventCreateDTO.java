/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author Šimon Baláž
 */
public class EventCreateDTO {

    private long id;

    @NotNull
    private ItemDTO item;

    private UserDTO owner;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfLoss;

    private String placeOfLoss;

    private UserDTO finder;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfFind;
    private String placeOfFind;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public Date getDateOfLoss() {
        return dateOfLoss;
    }

    public void setDateOfLoss(Date dateOfLoss) {
        this.dateOfLoss = dateOfLoss;
    }

    public String getPlaceOfLoss() {
        return placeOfLoss;
    }

    public void setPlaceOfLoss(String placeOfLoss) {
        this.placeOfLoss = placeOfLoss;
    }

    public UserDTO getFinder() {
        return finder;
    }

    public void setFinder(UserDTO finder) {
        this.finder = finder;
    }

    public Date getDateOfFind() {
        return dateOfFind;
    }

    public void setDateOfFind(Date dateOfFind) {
        this.dateOfFind = dateOfFind;
    }

    public String getPlaceOfFind() {
        return placeOfFind;
    }

    public void setPlaceOfFind(String placeOfFind) {
        this.placeOfFind = placeOfFind;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EventDTO)) {
            return false;
        }
        EventDTO other = (EventDTO) obj;
        if (placeOfFind != null ? !placeOfFind.equals(other.getPlaceOfFind()) : other.getPlaceOfFind() != null) {
            return false;
        }
        if (placeOfLoss != null ? !placeOfLoss.equals(other.getPlaceOfLoss()) : other.getPlaceOfLoss() != null) {
            return false;
        }
        if (dateOfLoss != null ? !dateOfLoss.equals(other.getDateOfLoss()) : other.getDateOfLoss() != null) {
            return false;
        }
        if (dateOfFind != null ? !dateOfFind.equals(other.getDateOfFind()) : other.getDateOfFind() != null) {
            return false;
        }
        if (finder != null ? !finder.equals(other.getFinder()) : other.getFinder() != null) {
            return false;
        }
        if (owner != null ? !owner.equals(other.getOwner()) : other.getOwner() != null) {
            return false;
        }
        return Objects.equals(this.item, other.getItem());
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(this.item);
        result = prime * result + (finder != null ? finder.hashCode() : 0);
        result = prime * result + (owner != null ? owner.hashCode() : 0);
        result = prime * result + item.hashCode();
        result = prime * result + (dateOfLoss != null ? dateOfLoss.hashCode() : 0);
        result = prime * result + (dateOfFind != null ? dateOfFind.hashCode() : 0);
        result = prime * result + (placeOfLoss != null ? placeOfLoss.hashCode() : 0);
        result = prime * result + (placeOfFind != null ? placeOfFind.hashCode() : 0);

        return result;
    }

}
