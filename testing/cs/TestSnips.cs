namespace COM.DZFSDev.Tests
{
    [TestClass]
    public class TestSnips.cs
    {
        [DataTestMethod]
        [TestCategory("Properties")]
        [TestCategory("PropertiesGet")]
        [DataRow("Prop", "Value", typeof(Type))]
        public void AllPropertiesGet_Returns(string property, string expectedString, Type type)
        {
            // Arrange
            SalesQuote target = new SalesQuote(1000, 100, 0.5M, Accessories.All, ExteriorFinish.Custom);
            PrivateObject privateObject = new PrivateObject(target);
            TypeConverter converter = TypeDescriptor.GetConverter(type);

            // Act
            var expected = converter.ConvertFrom(expectedString);

            // Assert
            var actual = privateObject.GetProperty(property);
            Assert.AreEqual(expected, actual);
        }
    }
}
