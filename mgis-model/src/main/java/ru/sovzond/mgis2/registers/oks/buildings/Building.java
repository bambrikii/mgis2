package ru.sovzond.mgis2.registers.oks.buildings;

import javax.persistence.Entity;
import javax.persistence.Table;

import ru.sovzond.mgis2.registers.oks.CapitalConstruction;

/**
 * @author Alexander Arakelyan Здание
 */
@Entity
@Table(name = "rosreg_oks_building")
public class Building extends CapitalConstruction {

}
