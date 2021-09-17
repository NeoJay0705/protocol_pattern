# Protocol Pattern

This protocol interface provides protocol makers to create an efficient and extendable protocol.

# Why It Is Efficient?

The protocol maker can define a default parser to extract contents from the client's input stream and a thread pool called a *runtime* to raise the throughput. 

# Why It Is Extendable

Users using the protocol from the protocol maker can replace the parser and runtime to make them more efficient. Just implementing the interfaces `IParser` and `IThreadPool` and setting them into the protocol instance from the protocol maker.

The interface provides a part to reverse a flow control to the users. Just implementing the interface `IFrameworkFlow`.
