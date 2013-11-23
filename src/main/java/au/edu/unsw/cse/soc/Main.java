package au.edu.unsw.cse.soc;
/*
 * Copyright (c) 2013, Denis Weerasiri All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import weka.classifiers.rules.Ridor;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * User: denis
 * TODO: Include the class description here
 */
public class Main {
    //static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        // Declare two numeric attributes
        Attribute Attribute1 = new Attribute("firstNumeric");
        Attribute Attribute2 = new Attribute("secondNumeric");

        // Declare a nominal attribute along with its values
        FastVector fvNominalVal = new FastVector(3);
        fvNominalVal.addElement("blue");
        fvNominalVal.addElement("gray");
        fvNominalVal.addElement("black");
        Attribute Attribute3 = new Attribute("aNominal", fvNominalVal);

        // Declare the class attribute along with its values
        FastVector fvClassVal = new FastVector(2);
        fvClassVal.addElement("positive");
        fvClassVal.addElement("negative");
        Attribute ClassAttribute = new Attribute("theClass", fvClassVal);

        // Declare the feature vector
        FastVector fvWekaAttributes = new FastVector(4);
        fvWekaAttributes.addElement(Attribute1);
        fvWekaAttributes.addElement(Attribute2);
        fvWekaAttributes.addElement(Attribute3);
        fvWekaAttributes.addElement(ClassAttribute);

        // Create an empty training set
        Instances isTrainingSet = new Instances("Rel", fvWekaAttributes, 10);
        // Set class index
        isTrainingSet.setClassIndex(3);

        // Create the instance
        Instance iExample1 = new Instance(4);
        iExample1.setValue((Attribute) fvWekaAttributes.elementAt(0), 1.0);
        iExample1.setValue((Attribute) fvWekaAttributes.elementAt(1), 0.5);
        iExample1.setValue((Attribute) fvWekaAttributes.elementAt(2), "gray");
        iExample1.setValue((Attribute) fvWekaAttributes.elementAt(3), "positive");
        // add the instance
        isTrainingSet.add(iExample1);

        // Create the instance
        Instance iExample2 = new Instance(4);
        iExample2.setValue((Attribute) fvWekaAttributes.elementAt(0), 1.0);
        iExample2.setValue((Attribute) fvWekaAttributes.elementAt(1), 0.5);
        iExample2.setValue((Attribute) fvWekaAttributes.elementAt(2), "blue");
        iExample2.setValue((Attribute) fvWekaAttributes.elementAt(3), "negative");
        // add the instance
        isTrainingSet.add(iExample2);

        // Create the instance
        Instance iExample3 = new Instance(4);
        iExample3.setValue((Attribute) fvWekaAttributes.elementAt(0), 1.0);
        iExample3.setValue((Attribute) fvWekaAttributes.elementAt(1), 0.5);
        iExample3.setValue((Attribute) fvWekaAttributes.elementAt(2), "black");
        iExample3.setValue((Attribute) fvWekaAttributes.elementAt(3), "negative");
        // add the instance
        isTrainingSet.add(iExample3);

        Ridor rdrTree = new Ridor();
        rdrTree.buildClassifier(isTrainingSet);

        System.out.println("The instance: " + rdrTree.toString());

        //rdrTree.buildClassifier();
    }

}
