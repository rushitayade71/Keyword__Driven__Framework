package com.bridgelabz.keyword_driven_framework;

import com.bridgelabz.keyword_driven_framework.baseclass.BaseClass;
import com.bridgelabz.keyword_driven_framework.keywordengineclass.KeywordEngine;
import org.testng.annotations.Test;

public class KeywordDrivenTest extends BaseClass {
        public KeywordEngine keywordEngine;

        @Test
        public void loginTest() throws Exception {
            keywordEngine = new KeywordEngine();
            keywordEngine.startExecution("login");
        }
        @Test
    public void registrationTest() throws Exception {
        keywordEngine = new KeywordEngine();
        keywordEngine.startExecution("registration");
        }
    }
