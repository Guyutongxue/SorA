 package cn.guyutongxue.aix.RichListView;
 
 import android.util.Log;
 import android.view.MotionEvent;
 import android.view.View;
 import android.view.View.OnClickListener;
 import android.view.View.OnLongClickListener;
 import android.view.View.OnTouchListener;
 import com.google.appinventor.components.annotations.DesignerComponent;
 import com.google.appinventor.components.annotations.DesignerProperty;
 import com.google.appinventor.components.annotations.PropertyCategory;
 import com.google.appinventor.components.annotations.SimpleEvent;
 import com.google.appinventor.components.annotations.SimpleFunction;
 import com.google.appinventor.components.annotations.SimpleObject;
 import com.google.appinventor.components.annotations.SimpleProperty;
 import com.google.appinventor.components.common.ComponentCategory;
 import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
 import com.google.appinventor.components.runtime.ButtonBase;
 import com.google.appinventor.components.runtime.Component;
 import com.google.appinventor.components.runtime.ComponentContainer;
 import com.google.appinventor.components.runtime.EventDispatcher;
 import com.google.appinventor.components.runtime.HVArrangement;
 import com.google.appinventor.components.runtime.HorizontalArrangement;
 import com.google.appinventor.components.runtime.Label;
 import com.google.appinventor.components.runtime.VerticalArrangement;
 import com.google.appinventor.components.runtime.VerticalScrollArrangement;
 import com.google.appinventor.components.runtime.util.YailList;
 import java.util.ArrayList;
 
 @DesignerComponent(version=2, description=
 	 	"<p>A new ListView that allow you to insert an icon and description to each element."+
 	 	"<br>The element is a list of: [IconFileName] Text [Description] ."+
 	 	"<br> Created by ColinTree. Modified by Guyutongxue.",
 	 category=ComponentCategory.EXTENSION, nonVisible=true, iconName="data:img/jpg;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA4RpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTM4IDc5LjE1OTgyNCwgMjAxNi8wOS8xNC0wMTowOTowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpkMzU1YTU5Yy05NWI1LWZlNDItODMxMy1lZjhiMzEyYmY4MWEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MjZGQ0VERjVEQ0E2MTFFN0FDNUY4RDVFNTY4QTRENzQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MjZGQ0VERjREQ0E2MTFFN0FDNUY4RDVFNTY4QTRENzQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6ZDM1NWE1OWMtOTViNS1mZTQyLTgzMTMtZWY4YjMxMmJmODFhIiBzdFJlZjpkb2N1bWVudElEPSJhZG9iZTpkb2NpZDpwaG90b3Nob3A6NTI0ZTU4MDctZGNhNS0xMWU3LTk1ZGYtOWU2NWMwYjBmYTEwIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+7rkUbAAAAIFQTFRF9fX18/Pz7+/v8fHx6urqjmsb7u7u5OTkjHgZ9PT0+Pj4v7+/+/v7i3EV2N/B9vb2knIkzNep4uLi8fXn1tbWj28Xk30g4+7Knoc42dnZ5e7R2tramHkq19fX8PDw0tLS1NTU6+vr7Ozs6enp7e3tubm5VlZWra2turq66Ojo////icR3OAAAACt0Uk5T////////////////////////////////////////////////////////ACPJp9AAAABqSURBVHjanMzHDoAgEEVR7L33LoqF+f8PFIWEjW68uznJGwQApwwAsdvP3AgpmkrwerxDVfTq/iSgLWuza4Z8nigH5DiKNppsIYCQZcH6SlkcwiC2+A8BiW2k2533Nfnxg8reAQ4ZwCXAAMjeIWm6Ye4hAAAAAElFTkSuQmCC")
 @SimpleObject(external=true)
 public class RichListView
   extends AndroidNonvisibleComponent
   implements Component
 {
   public static final int VERSION = 2;
   private static final String LOG_TAG = "RichListView";
   private final ArrayList<Element> elementList = new ArrayList();
   private HVArrangement vaContainer;
   private int currentListSize = 0;
   
   public RichListView(ComponentContainer container) {
     super(container.$form());
     Log.d("RichListView", "RichListView Created");
   }
   
   @SimpleFunction
   public void Initialize(VerticalArrangement verticalArrangement) {
     this.vaContainer = verticalArrangement;
     this.vaContainer.AlignHorizontal(3);
   }
   
   @SimpleFunction
   public void Initialize_Scroll(VerticalScrollArrangement verticalScrollArrangement) { this.vaContainer = verticalScrollArrangement;
     this.vaContainer.AlignHorizontal(3);
   }
   
   @SimpleFunction
   public void Clear() {
     Set(YailList.makeEmptyList());
   }
   
   @SimpleFunction
   public void Set(YailList list) {
     int size = list.size();
     
 
     for (int i = 0; i < size; i++) {
       Object sublistElement = list.getObject(i);
       if ((sublistElement instanceof YailList)) {
         if (this.currentListSize > i) {
           SetElement(i + 1, (YailList)sublistElement);
         } else {
           AddElement((YailList)sublistElement);
         }
       }
       else if (this.currentListSize > i) {
         SetElement(i + 1, getYailList(new Object[] { sublistElement }));
       }
       else {
         AddElement(getYailList(new Object[] { sublistElement }));
       }
     }
     
 
     for (int i = list.size(); i < this.currentListSize; i++) {
       ((Element)this.elementList.get(i)).hide();
     }
     this.currentListSize = list.size();
   }
   
   private YailList getYailList(Object... obj) { return YailList.makeList(obj); }
   
   @SimpleFunction
   public void AddElement(YailList element)
   {
     int elementListSize = this.elementList.size();
     if ((this.currentListSize < elementListSize) && (elementListSize > 0)) {
       Element ele = (Element)this.elementList.get(this.currentListSize);
       ele.show();
       ele.set(element);
     } else {
       final int elementIndex = this.currentListSize;
       this.elementList.add(new Element(this.vaContainer, element)
       {
         public void onElementClick() {
           RichListView.this.ElementClick(elementIndex);
         }
         
         public boolean onElementLongClick() {
           return RichListView.this.ElementLongClick(elementIndex);
         }
         
         public void onElementTouchDown() {
           RichListView.this.ElementTouchDown(elementIndex);
         }
         
         public void onElementTouchUp() {
           RichListView.this.ElementTouchUp(elementIndex);
         }
         
         public void onIconClick() {
           RichListView.this.IconClick(elementIndex);
         }
         
         public boolean onIconLongClick() {
           return RichListView.this.IconLongClick(elementIndex);
         }
         
         public void onIconTouchDown() {
           RichListView.this.IconTouchDown(elementIndex);
         }
         
         public void onIconTouchUp() {
           RichListView.this.IconTouchUp(elementIndex);
         }
       });
     }
     this.currentListSize += 1;
   }
   
   @SimpleFunction
   public void AddEmptyElement() { AddElement(YailList.makeEmptyList()); }
   
   @SimpleFunction
   public void SetElement(int elementIndex, YailList element)
   {
     if ((elementIndex < 1) || (elementIndex > this.currentListSize)) {
       return;
     }
     Element ele = (Element)this.elementList.get(elementIndex - 1);
     ele.show();
     ele.set(element);
   }
   
   @SimpleFunction
   public void SetElementText(int elementIndex, String text) { if ((elementIndex < 1) || (elementIndex > this.currentListSize)) {
       return;
     }
     ((Element)this.elementList.get(elementIndex - 1)).setText(text);
   }
   
   @SimpleFunction
   public void SetElementMainText(int elementIndex, String mainText) { if ((elementIndex < 1) || (elementIndex > this.currentListSize)) {
       return;
     }
     ((Element)this.elementList.get(elementIndex - 1)).setMainText(mainText);
   }
   
   @SimpleFunction
   public void SetElementSubText(int elementIndex, String subText) { if ((elementIndex < 1) || (elementIndex > this.currentListSize)) {
       return;
     }
     ((Element)this.elementList.get(elementIndex - 1)).setSubText(subText);
   }
   
   @SimpleFunction
   public void SetElementIcon(int elementIndex, String path) { if ((elementIndex < 1) || (elementIndex > this.currentListSize)) {
       return;
     }
     ((Element)this.elementList.get(elementIndex - 1)).setIcon(path);
   }
   
   @SimpleFunction
   public YailList GetElement(int elementIndex) { return YailList.makeList(getElementWithObject(elementIndex)); }
   
   public Object[] getElementWithObject(int elementIndex) {
     Element element = (Element)this.elementList.get(elementIndex - 1);
     int size = element.size();
     if (size == 1) {
       return new Object[] {element
         .getText() };
     }
     if (size == 2) {
       return new Object[] {element
         .getIcon(), element
         .getText() };
     }
     if (size == 3) {
       return new Object[] {element
         .getIcon(), element
         .getMainText(), element
         .getSubText() };
     }
     
     return new Object[0];
   }
   
   @SimpleFunction
   public void RemoveElement(int elementIndex) {
     if ((elementIndex < 1) || (elementIndex > this.currentListSize)) {
       return;
     }
     for (int i = elementIndex - 1; i < this.currentListSize - 1; i++) {
       copyElement(i + 1, i);
     }
     ((Element)this.elementList.get(this.currentListSize - 1)).hide();
     this.currentListSize -= 1;
   }
   
   private void copyElement(int indexFrom, int indexTo) { SetElement(indexTo + 1, 
     
       getYailList(GetElement(indexFrom + 1).toArray()));
   }
   
 
 
   @SimpleEvent
   public void ElementClick(int elementIndex)
   {
     EventDispatcher.dispatchEvent(this, "ElementClick", new Object[] { Integer.valueOf(1 + elementIndex) });
   }
   
   @SimpleEvent
   public boolean ElementLongClick(int elementIndex) { return EventDispatcher.dispatchEvent(this, "ElementLongClick", new Object[] { Integer.valueOf(1 + elementIndex) }); }
   
   @SimpleEvent
   public boolean ElementTouchDown(int elementIndex) {
     return EventDispatcher.dispatchEvent(this, "ElementTouchDown", new Object[] { Integer.valueOf(1 + elementIndex) });
   }
   
   @SimpleEvent
   public boolean ElementTouchUp(int elementIndex) { return EventDispatcher.dispatchEvent(this, "ElementTouchUp", new Object[] { Integer.valueOf(1 + elementIndex) }); }
   
   @SimpleEvent
   public void IconClick(int elementIndex)
   {
     EventDispatcher.dispatchEvent(this, "IconClick", new Object[] { Integer.valueOf(1 + elementIndex) });
   }
   
   @SimpleEvent
   public boolean IconLongClick(int elementIndex) { return EventDispatcher.dispatchEvent(this, "IconLongClick", new Object[] { Integer.valueOf(1 + elementIndex) }); }
   
   @SimpleEvent
   public void IconTouchDown(int elementIndex) {
     EventDispatcher.dispatchEvent(this, "IconTouchDown", new Object[] { Integer.valueOf(1 + elementIndex) });
   }
   
   @SimpleEvent
   public void IconTouchUp(int elementIndex) { EventDispatcher.dispatchEvent(this, "IconTouchUp", new Object[] { Integer.valueOf(1 + elementIndex) }); }
   
 
 
 
 
   private int elementHeight = 57;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int ElementHeight() { return this.elementHeight; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_integer", defaultValue="57")
   public void ElementHeight(int height) {
     this.elementHeight = height;
     refreshElementProperties();
   }
   
   private int elementTouchDownColor = 0;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int TouchDownColor() { return this.elementTouchDownColor; }
   
   @SimpleProperty
   @DesignerProperty(editorType="color", defaultValue="&H00000000")
   public void TouchDownColor(int argb) {
     this.elementTouchDownColor = argb;
     refreshElementProperties();
   }
   
   private int elementWidthBeforeIcon = 7;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int WidthBeforeIcon() { return this.elementWidthBeforeIcon; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_integer", defaultValue="7")
   public void WidthBeforeIcon(int width) {
     this.elementWidthBeforeIcon = width;
     refreshElementProperties();
   }
   
   private int elementWidthAfterIcon = 5;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int WidthAfterIcon() { return this.elementWidthAfterIcon; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_integer", defaultValue="5")
   public void WidthAfterIcon(int width) {
     this.elementWidthAfterIcon = width;
     refreshElementProperties();
   }
   
   private int elementIconWidth = 40;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int IconWidth() { return this.elementIconWidth; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_integer", defaultValue="40")
   public void IconWidth(int width) {
     this.elementIconWidth = width;
     refreshElementProperties();
   }
   
   private int elementIconHeight = 40;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int IconHeight() { return this.elementIconHeight; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_integer", defaultValue="40")
   public void IconHeight(int height) {
     this.elementIconHeight = height;
     refreshElementProperties();
   }
   
   private int elementTextColor = -16777216;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int TextColor() { return this.elementTextColor; }
   
   @SimpleProperty
   @DesignerProperty(editorType="color", defaultValue="&HFF000000")
   public void TextColor(int argb) {
     this.elementTextColor = argb;
     refreshElementProperties();
   }
   
   private float elementTextFontSize = 14.0F;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public float TextFontSize() { return this.elementTextFontSize; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_float", defaultValue="14")
   public void TextFontSize(float size) {
     this.elementTextFontSize = size;
     refreshElementProperties();
   }
   
   private boolean elementTextFontBold = false;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public boolean TextFontBold() { return this.elementTextFontBold; }
   
   @SimpleProperty
   @DesignerProperty(editorType="boolean", defaultValue="false")
   public void TextFontBold(boolean bold) {
     this.elementTextFontBold = bold;
     refreshElementProperties();
   }
   
   private int elementSubTextColor = -3355444;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int SubTextColor() { return this.elementSubTextColor; }
   
   @SimpleProperty
   @DesignerProperty(editorType="color", defaultValue="&HFFCCCCCC")
   public void SubTextColor(int argb) {
     this.elementSubTextColor = argb;
     refreshElementProperties();
   }
   
   private float elementSubTextFontSize = 12.0F;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public float SubTextFontSize() { return this.elementSubTextFontSize; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_float", defaultValue="12")
   public void SubTextFontSize(float size) {
     this.elementSubTextFontSize = size;
     refreshElementProperties();
   }
   
   private boolean elementSubTextFontBold = false;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public boolean SubTextFontBold() { return this.elementSubTextFontBold; }
   
   @SimpleProperty
   @DesignerProperty(editorType="boolean", defaultValue="false")
   public void SubTextFontBold(boolean bold) {
     this.elementSubTextFontBold = bold;
     refreshElementProperties();
   }
   
   private int elementUnderlineColor = -3355444;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int UnderlineColor() { return this.elementUnderlineColor; }
   
   @SimpleProperty
   @DesignerProperty(editorType="color", defaultValue="&HFFCCCCCC")
   public void UnderlineColor(int argb) {
     this.elementUnderlineColor = argb;
     refreshElementProperties();
   }
   
   private int elementUnderlineWidth = 1;
   
   @SimpleProperty(category=PropertyCategory.APPEARANCE)
   public int UnderlineWidth() { return this.elementUnderlineWidth; }
   
   @SimpleProperty
   @DesignerProperty(editorType="non_negative_integer", defaultValue="1")
   public void UnderlineWidth(int lineWidth) {
     this.elementUnderlineWidth = lineWidth;
     refreshElementProperties();
   }
   
   private void refreshElementProperties() {
     for (int i = 0; i < this.currentListSize; i++) {
       ((Element)this.elementList.get(i)).refreshProperties();
     }
   }
   
   private class Element implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener
   {
     private ComponentContainer container;
     private HorizontalArrangement ha;
     private Label labelBeforeIcon;
     private ButtonBase icon;
     private Label labelAfterIcon;
     private Label size2Label;
     private VerticalArrangement size3Va;
     private Label size3MainText;
     private Label size3SubText;
     private Label labelAfterText;
     private Label underline;
     
     public Element(ComponentContainer container, YailList list)
     {
       this.container = container;
       create();
       set(list);
     }
     
     public void onElementClick() {}
     
     public boolean onElementLongClick() { return false; }
     public void onElementTouchDown() {}
     public void onElementTouchUp() {}
     public void onIconClick() {}
     public boolean onIconLongClick() { return false; }
     
     public void onIconTouchDown() {}
     
     public void onIconTouchUp() {}
     
     public void onClick(View v) { onElementClick(); }
     
 
 
     public boolean onLongClick(View v) { return onElementLongClick(); }
     
     private void ElementTouchDown() {
       this.ha.BackgroundColor(RichListView.this.elementTouchDownColor);
       onElementTouchDown();
     }
     
     private void ElementTouchUp() { this.ha.BackgroundColor(16777215);
       onElementTouchUp();
     }
     
     public boolean onTouch(View v, MotionEvent event) {
       int action = event.getActionMasked();
       if (action == 0) {
         ElementTouchDown();
       } else if ((action == 1) || (action == 3)) {
         ElementTouchUp();
       }
       return false;
     }
     
     private void create() {
       if (this.ha != null) {
         return;
       }
       this.ha = new HorizontalArrangement(this.container);
       this.ha.getView().setOnClickListener(this);
       this.ha.getView().setOnLongClickListener(this);
       this.ha.getView().setOnTouchListener(this);
       this.ha.AlignVertical(2);
       this.ha.Width(-2);
       this.labelBeforeIcon = new Label(this.ha);
       this.labelBeforeIcon.Text("");
       this.icon = new ButtonBase(this.ha) {
         public void click() {
           RichListView.Element.this.onIconClick();
         }
         
         public boolean longClick() { return RichListView.Element.this.onIconLongClick(); }
         
         public void TouchDown()
         {
           RichListView.Element.this.onIconTouchDown();
         }
         
         public void TouchUp() {
           RichListView.Element.this.onIconTouchUp();
         }
       };
       this.icon.Shape(1);
       this.icon.Text("");
       this.labelAfterIcon = new Label(this.ha);
       this.labelAfterIcon.Text("");
       this.size2Label = new Label(this.ha);
       this.size2Label.Width(-2);
       this.size2Label.Text("Element Text");
       this.size2Label.TextAlignment(0);
       this.size2Label.BackgroundColor(16777215);
       this.size3Va = new VerticalArrangement(this.ha);
       this.size3Va.AlignVertical(2);
       this.size3Va.AlignHorizontal(1);
       this.size3Va.Width(-2);
       this.size3MainText = new Label(this.size3Va);
       this.size3MainText.Height(18);
       this.size3MainText.Text("Element Main Text");
       this.size3MainText.TextAlignment(0);
       this.size3SubText = new Label(this.size3Va);
       this.size3SubText.Height(18);
       this.size3SubText.Text("Element Sub Text");
       this.size3SubText.TextAlignment(0);
       this.labelAfterText = new Label(this.ha);
       this.labelAfterText.Text("");
       this.labelAfterText.Width(10);
       this.underline = new Label(this.container);
       this.underline.Width(-2);
       this.underline.Text("");
       this.underline.HasMargins(false);
       refreshProperties();
     }
     
     public void refreshProperties() { show();
       this.ha.Height(RichListView.this.elementHeight);
       this.labelBeforeIcon.Width(RichListView.this.elementWidthBeforeIcon);
       this.icon.Height(RichListView.this.elementIconHeight);
       this.icon.Width(RichListView.this.elementIconWidth);
       this.labelAfterIcon.Width(RichListView.this.elementWidthAfterIcon);
       this.size2Label.TextColor(RichListView.this.elementTextColor);
       this.size2Label.FontSize(RichListView.this.elementTextFontSize);
       this.size2Label.FontBold(RichListView.this.elementTextFontBold);
       this.size3MainText.TextColor(RichListView.this.elementTextColor);
       this.size3MainText.FontSize(RichListView.this.elementTextFontSize);
       this.size3MainText.FontBold(RichListView.this.elementTextFontBold);
       this.size3SubText.TextColor(RichListView.this.elementSubTextColor);
       this.size3SubText.FontSize(RichListView.this.elementSubTextFontSize);
       this.size3SubText.FontBold(RichListView.this.elementSubTextFontBold);
       this.underline.BackgroundColor(RichListView.this.elementUnderlineColor);
       this.underline.Height(RichListView.this.elementUnderlineWidth);
     }
     
     public void show() {
       this.ha.Visible(Boolean.valueOf(true));
       this.underline.Visible(Boolean.valueOf(true));
     }
     
     public void hide() { this.ha.Visible(Boolean.valueOf(false));
       this.underline.Visible(Boolean.valueOf(false));
     }
     
     public int size() {
       if (this.size2Label.Visible() == true) {
         if (this.icon.Visible() == true) {
           return 2;
         }
         return 1;
       }
       
       return 3;
     }
     
     public void setIcon(String path)
     {
       if (size() > 1)
         this.icon.Image(path);
     }
     
     public String getIcon() {
       return size() > 1 ? this.icon.Image() : "";
     }
     
     public void setText(String text) {
       if (size() < 3) {
         this.size2Label.Text(text);
       } else
         setMainText(text);
     }
     
     public String getText() {
       return size() < 3 ? this.size2Label.Text() : getMainText();
     }
     
     public void setMainText(String text) {
       if (size() < 3) {
         setText(text);
       } else
         this.size3MainText.Text(text);
     }
     
     public String getMainText() {
       return size() < 3 ? getText() : this.size3MainText.Text();
     }
     
     public void setSubText(String text) {
       if (size() < 3) {
         setText(text);
       } else
         this.size3SubText.Text(text);
     }
     
     public String getSubText() {
       return size() < 3 ? getText() : this.size3SubText.Text();
     }
     
     public void set(YailList list) {
       int size = list.toArray().length;
       switch (size) {
       case 1: 
         toSize1(list);
         break;
       case 2: 
         toSize2(list);
         break;
       default: 
         if (size < 1) {
           toSize1("ERROR");
         } else
           toSize3(list);
         break; }
     }
     
     public void toSize1() {
       this.icon.Visible(Boolean.valueOf(false));
       this.size2Label.Visible(Boolean.valueOf(true));
       this.size3Va.Visible(Boolean.valueOf(false));
     }
     
     public void toSize1(String text) { toSize1();
       this.size2Label.Text(text);
     }
     
     public void toSize1(YailList list) { toSize1(list.getString(0)); }
     
     public void toSize2() {
       this.icon.Visible(Boolean.valueOf(true));
       this.size2Label.Visible(Boolean.valueOf(true));
       this.size3Va.Visible(Boolean.valueOf(false));
     }
     
     public void toSize2(String iconPath, String text) { toSize2();
       this.icon.Image(iconPath);
       this.size2Label.Text(text);
     }
     
     public void toSize2(YailList list) { toSize2(list.getString(0), list.getString(1)); }
     
     public void toSize3() {
       this.icon.Visible(Boolean.valueOf(true));
       this.size2Label.Visible(Boolean.valueOf(false));
       this.size3Va.Visible(Boolean.valueOf(true));
     }
     
     public void toSize3(String iconPath, String mainText, String subText) { toSize3();
       if(iconPath==""){
       	   this.icon.Visible(Boolean.valueOf(false));
       }
       else{
       	   this.icon.Image(iconPath);
       }
       this.size3MainText.Text(mainText);
       this.size3SubText.Text(subText);
     }
     
     public void toSize3(YailList list) { toSize3(list.getString(0), list.getString(1), list.getString(2)); }
   }
 }