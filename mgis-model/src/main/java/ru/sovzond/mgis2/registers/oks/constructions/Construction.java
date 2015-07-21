package ru.sovzond.mgis2.registers.oks.constructions;

import javax.persistence.Entity;
import javax.persistence.Table;

import ru.sovzond.mgis2.registers.oks.CapitalConstruction;

/**
 * @author Alexander Arakelyan Сооружение
 */
@Entity
@Table(name = "rosreg_oks_construction")
public class Construction extends CapitalConstruction {

}
