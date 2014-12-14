'use strict';

describe('Service: CustomResource', function () {

  // load the service's module
  beforeEach(module('angularFrontendApp'));

  // instantiate service
  var customResource;
  beforeEach(inject(function (_customResource_) {
    customResource = _customResource_;
  }));

  it('should do something', function () {
    expect(!!customResource).toBe(true);
  });

});