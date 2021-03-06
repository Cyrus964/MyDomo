var masterStore = null;
var cacheStore = null;

define(["dojo/_base/declare",
	"dojo/store/Memory",
	"dojo/store/Cache",
	"dojo/store/JsonRest"
], function(declare, Memory, Cache, JsonRest){

	if (masterStore == null) {
		masterStore = new JsonRest({
			target: "/house"
		});
		cacheStore = new Memory({ });
	}
	
	return declare(null, {
		// summary:
		//		This is a store for House data. It implements dojo/store/api/Store.
		baseClass: "house",
		houseStore: null,
		labels: null,
		group: null,
		load: function(url) {
		},

		constructor: function(options){
			// summary:
			//		This is a basic store for RESTful communicating with a server through JSON
			//		formatted data.
			this.headers = {};
			declare.safeMixin(this, options);
			
			this.houseStore = new Cache(masterStore, cacheStore);
			var oldPut = this.houseStore.put;
			this.houseStore.put = function(object, options){
				if(object.id == null){
					throw new Error("Id must be provided");
				}
				// now call the original
				oldPut.call(this, object, options);
			};
		},

		// idProperty: String
		//		Indicates the property to use as the identity property. The values of this
		//		property should be unique.
		idProperty: "id",

		get: function(id){
			// summary:
			//		Retrieves an object by its identity. This will trigger a GET request to the server using
			//		the url `this.target + id`.
			// id: Number
			//		The identity to use to lookup the object
			// returns: Object
			//		The object in the store that matches the given id.
			return this.houseStore.get(id);
		},

		getIdentity: function(object){
			// summary:
			//		Returns an object's identity
			// object: Object
			//		The object to get the identity from
			// returns: Number
			return object[this.idProperty];
		},

		put: function(object, options){
			// summary:
			//		Stores an object. This will trigger a PUT request to the server
			//		if the object has an id, otherwise it will trigger a POST request.
			// object: Object
			//		The object to store.
			// returns: dojo/_base/Deferred
			return this.houseStore.put(object, options);
		},

		add: function(object, options){
			// summary:
			//		Adds an object. This will trigger a PUT request to the server
			//		if the object has an id, otherwise it will trigger a POST request.
			// object: Object
			//		The object to store.
			// options: __PutDirectives?
			//		Additional metadata for storing the data.  Includes an "id"
			//		property if a specific id is to be used.
			return this.add(object, options);
		},

		remove: function(id){
			// summary:
			//		Deletes an object by its identity. This will trigger a DELETE request to the server.
			// id: Number
			//		The identity to use to delete the object
			return this.houseStore.remove(id);
		},

		query: function(query, options){
			// summary:
			//		Queries the store for objects. This will trigger a GET request to the server, with the
			//		query added as a query string.
			// query: Object
			//		The query to use for retrieving objects from the store.
			// options: __QueryOptions?
			//		The optional arguments to apply to the resultset.
			// returns: dojo/store/api/Store.QueryResults
			//		The results of the query, extended with iterative methods.
			return this.houseStore.query(query, options);
		},
		
		transaction: function(){
			// summary:
			//		Starts a new transaction.
			//		Note that a store user might not call transaction() prior to using put,
			//		delete, etc. in which case these operations effectively could be thought of
			//		as "auto-commit" style actions.
			// returns: dojo/store/api/Store.Transaction
			//		This represents the new current transaction.
			return this.houseStore.transaction();
		},
		getChildren: function(parent, options){
			// summary:
			//		Retrieves the children of an object.
			// parent: Object
			//		The object to find the children of.
			// options: dojo/store/api/Store.QueryOptions?
			//		Additional options to apply to the retrieval of the children.
			// returns: dojo/store/api/Store.QueryResults
			//		A result set of the children of the parent object.
			return this.houseStore.getChildren();
		},
		getMetadata: function(object){
			// summary:
			//		Returns any metadata about the object. This may include attribution,
			//		cache directives, history, or version information.
			// object: Object
			//		The object to return metadata for.
			// returns: Object
			//		An object containing metadata.
			return this.houseStore.getMetadata();
		}
	});
});