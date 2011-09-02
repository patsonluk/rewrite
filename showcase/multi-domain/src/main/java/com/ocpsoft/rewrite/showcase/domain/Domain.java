package com.ocpsoft.rewrite.showcase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Domain implements java.io.Serializable
{
   private static final long serialVersionUID = 1L;
   @Id
   private @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   Long id = null;
   @Version
   private @Column(name = "version")
   int version = 0;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Column
   private String description;

   public String getDescription()
   {
      return this.description;
   }

   public void setDescription(final String description)
   {
      this.description = description;
   }

   @Column
   private int annualRevenue;

   public int getAnnualRevenue()
   {
      return this.annualRevenue;
   }

   public void setAnnualRevenue(final int annualRevenue)
   {
      this.annualRevenue = annualRevenue;
   }

   @Column
   private String location;

   public String getLocation()
   {
      return this.location;
   }

   public void setLocation(final String location)
   {
      this.location = location;
   }

   @Column
   private String name;

   public String getName()
   {
      return this.name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   @Override
   public String toString()
   {
      return "Domain [name=" + name + ", description=" + description + ", location=" + location + ", annualRevenue="
               + annualRevenue + "]";
   }
}
