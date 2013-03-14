package ru.yandex.qatools.htmlelements.matchers.position;

import org.openqa.selenium.Point;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

@SuppressWarnings("unused")
class ElementPositionHelper {

    private Point leftTop;
    private Point rightBottom;

    public ElementPositionHelper(HtmlElement origin) {

        this.leftTop = origin.getLocation();
        rightBottom = new Point(
                leftTop.getX() + origin.getSize().getWidth(),
                leftTop.getY() + origin.getSize().getHeight());
    }

    public int getLeftIndent() {
        return leftTop.getX();
    }

    public Point getLeftBottom() {
        return new Point(leftTop.getX(), rightBottom.getY());
    }

    public Point getRightTop() {
        return new Point(rightBottom.getX(), leftTop.getY());
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    public int getWidth() {
        return rightBottom.getX() - leftTop.getX();
    }

    public int getHeight() {
        return rightBottom.getY() - leftTop.getY();
    }

    public int compareHorizontal(ElementPositionHelper second) {
        return this.getLeftIndent() - second.getLeftIndent();
    }

    public boolean isIntercepts(HtmlElement element) {
        ElementPositionHelper second = new ElementPositionHelper(element);
        return isPointInside(second.getLeftBottom())
                || isPointInside(second.getLeftTop())
                || isPointInside(second.getRightBottom())
                || isPointInside(second.getRightTop());
    }

    private boolean isPointInside(Point point) {
        return point.getX() > leftTop.getX()
                && point.getX() < rightBottom.getX()
                && point.getY() < rightBottom.getY()
                && point.getY() > leftTop.getY();
    }

    public boolean isElementInside(HtmlElement element) {
        ElementPositionHelper second = new ElementPositionHelper(element);
        return isPointInside(second.getLeftBottom())
                && isPointInside(second.getRightBottom())
                && isPointInside(second.getLeftTop())
                && isPointInside(second.getRightTop());
    }

    public boolean isLower(ElementPositionHelper second) {
        return leftTop.getY() < second.rightBottom.getY();
    }

    public boolean isHigher(ElementPositionHelper second) {
        return rightBottom.getY() > second.leftTop.getY();
    }

    public boolean isLefter(ElementPositionHelper second) {
        return getLeftIndent() < second.getLeftIndent()
                && rightBottom.getX() < second.getRightBottom().getX();
    }

    public int getTopIndent() {
        return leftTop.getY();
    }

}
