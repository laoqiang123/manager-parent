/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.ServiceApplicationContext;
import cz.muni.fi.pa165.dto.CategoryCreateDTO;
import cz.muni.fi.pa165.dto.CategoryDTO;
import cz.muni.fi.pa165.dto.ItemCreateDTO;
import cz.muni.fi.pa165.dto.ItemDTO;
import cz.muni.fi.pa165.dto.QuestionsDTO;
import cz.muni.fi.pa165.dto.enums.ItemColor;
import cz.muni.fi.pa165.facade.ItemFacade;
import cz.muni.fi.pa165.facade.CategoryFacade;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author robhavlicek
 */
@ContextConfiguration(classes = ServiceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ItemFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ItemFacade itemFacade;

    @Autowired
    private CategoryFacade categoryFacade;

    private ItemDTO testItem;
    private CategoryDTO testCategory;

    @BeforeMethod
    public void setup() {

        CategoryCreateDTO category = new CategoryCreateDTO();
        category.setName("Electronics");
        category.setDescription("Various types of electronics");
        categoryFacade.createCategory(category);
        testCategory = categoryFacade.getAllCategories().get(0);
        ItemCreateDTO item = new ItemCreateDTO();
        item.setCategory(testCategory);
        item.setColor(ItemColor.GREEN);
        item.setName("Notebook");
        item.setDescription("DELL laptop");
        item.setDepth(1);
        item.setWeight(BigDecimal.ONE);
        item.setHeight(1);
        item.setWidth(1);
        item.setPhotoUri("test");

        itemFacade.create(item);
        testItem = itemFacade.getAllItems().get(0);
    }

    @Test
    public void testGetAllItems() {

        List<ItemDTO> retrievedItems = itemFacade.getAllItems();
        Assert.assertEquals(retrievedItems.size(), 1);
    }

    @Test
    public void testGetItemByCategory() {
        List<ItemDTO> retrievedItems = itemFacade.findByCategory(testCategory.getId());
        Assert.assertEquals(retrievedItems.size(), 1);
    }

    @Test
    public void testGetItemById() {
        ItemDTO returnedItem = itemFacade.findById(testItem.getId());
        Assert.assertEquals(returnedItem.getName(), testItem.getName());
    }

    @Test
    public void testRemoveItem() {
        ItemDTO returnedItem = itemFacade.findById(testItem.getId());
        itemFacade.remove(returnedItem.getId());
        List<ItemDTO> retrievedItems = itemFacade.findByCategory(testCategory.getId());
        Assert.assertEquals(retrievedItems.size(), 0);
    }

    @Test
    public void testReturnItem() {
        itemFacade.itemReturnedToOwner(testItem.getId());
        ItemDTO returnedItem = itemFacade.findById(testItem.getId());

        Assert.assertNotNull(returnedItem.getReturned());
    }

    @Test
    public void testUpdateItem() {

        testItem.setDepth(55);
        testItem.setHeight(22);
        testItem.setDescription("zmena popisu");
        itemFacade.update(testItem);
        ItemDTO returnedItem = itemFacade.findById(testItem.getId());
        Assert.assertEquals(returnedItem.getDepth(), 55);
        Assert.assertEquals(returnedItem.getHeight(), 22);
        Assert.assertEquals(returnedItem.getDescription(), "zmena popisu");
    }

    @Test
    public void testQuestionsItem() {
        QuestionsDTO questions = new QuestionsDTO();
        questions.setDepth(1);
        questions.setHeight(1);
        questions.setWidth(1);
        questions.setColor(ItemColor.GREEN);
        questions.setWeight(BigDecimal.ONE);
        boolean result = itemFacade.canBeReturned(testItem.getId(), questions);
        Assert.assertTrue(result);
        questions.setColor(ItemColor.BLACK);
        questions.setHeight(21);
        questions.setWidth(21);
        result = itemFacade.canBeReturned(testItem.getId(), questions);
        Assert.assertFalse(result);

    }

}
