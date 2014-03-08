//This is the attribute class of the actor. For example, whether actors are children, adults, beautiful etc.

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class ActorAttribute{

    public String  label;
    public String  sNicName;
    public boolean bIsMale;
    public boolean bIsFemale;
    public boolean bIsChildren;
    public boolean bIsAdult;
    public boolean bIsParent;
    public boolean bIsBeautiful;
    public boolean bIsSexy;
    public boolean bIsSmart;
    public String  sHairStyle;
    public String  sDressStyle;
    public boolean bIsFat;
    public boolean bIsShort;

    public ActorAttribute(String sLabel) {
        this.label 	= sLabel;
	sNicName 	= "";
	bIsMale 	= false;
	bIsFemale 	= false;
	bIsChildren 	= false;
	bIsAdult 	= false;
	bIsParent 	= false;
	bIsBeautiful 	= false;
	bIsSexy 	= false;
	bIsSmart 	= false;
	sHairStyle 	= "";
	sDressStyle 	= "";
	bIsFat 		= false;
	bIsShort 	= false;
    }

    public ActorAttribute() {
        label 		= "";
	sNicName 	= "";
	bIsMale 	= false;
	bIsFemale 	= false;
	bIsChildren 	= false;
	bIsAdult 	= false;
	bIsParent 	= false;
	bIsBeautiful 	= false;
	bIsSexy 	= false;
	bIsSmart 	= false;
	sHairStyle 	= "";
	sDressStyle 	= "";
	bIsFat 		= false;
	bIsShort 	= false;
    }

}

