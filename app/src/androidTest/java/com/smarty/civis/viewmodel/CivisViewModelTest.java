package com.smarty.civis.viewmodel;

import android.content.ContentResolver;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.smarty.civis.models.User;
import com.smarty.civis.utils.CivisViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.*;

/**
 * Created by mohammed on 6/27/17.
 */

/*
    This Test meant to be for local usage it will affect the current DB
    So please DONT Run THis Test
 */
@RunWith(AndroidJUnit4.class)
public class CivisViewModelTest
{
    CivisViewModel civisViewModel;
    ContentResolver contentResolver;


    @Before
    public void setUp() throws Exception
    {
        contentResolver = InstrumentationRegistry.getTargetContext().getContentResolver();
        civisViewModel = CivisViewModel.getInstance(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void saveUserTest() throws Exception
    {
        User user = new User();
        user.setFirstName("Mohammed");
        user.setLastName("Abuiriban");
        user.setEmail("m.g.iriban@gmail.com");
        user.setPhone("+4912345678");

        long id = civisViewModel.saveUser(user);
        assertEquals(1L, id);
    }

    @Test
    public void getUserTest() throws Exception
    {
        User user = civisViewModel.getUser(1);
        assertEquals("Mohammed", user.getFirstName());
        assertEquals("Abuiriban", user.getLastName());
        assertEquals("+4912345678", user.getPhone());
        assertEquals("m.g.iriban@gmail.com", user.getEmail());
        assertEquals(1, user.getId());
    }



}
