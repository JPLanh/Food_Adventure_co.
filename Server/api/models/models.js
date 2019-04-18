'use strict'
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var user = new Schema (
	{
		userUID:
		{
			type: Number,
			required: 'A Unique user ID is required',
//			unique: true,
//			dropDups: true
		},
		userName:
		{
			type: String
		},
		password:
		{
			type: String
		},
		firstName:
		{
			type: String,
			required: 'A first name is required'
		},
		lastName:
		{
			type: String,
			required: 'A password is required'
		},
		email:
		{
			type: String,
			required: 'An email is required'
		},
		phone:
		{
			type: Number,
			required: 'A phone number is required'
		},
		dateOfBirth:
		{
			type: Date,
			required: 'A date of birth is required'
		},
		coins:
		{
			type: Number,
			default: 0
		},
		tier:
		{
			type: Number,
			default: 1
		},
		diamonds:
		{
			type: Number,
			default: 0
		},
		frozen:
		{
			type: Boolean,
			default: false
		}
	},
	{ 
		collection: 'User',
	}
);
var rewards = new Schema(
	{
		name:
		{
			type: String,
			required: "A item name is required"
		},
		coin:
		{
			type: Number,
			default: 0
		},
		diamond:
		{
			type: Number,
			default: 0
		},
		tier:
		{
			type: Number,
			default: 0
		},
		image:
		{
			type: String
		},
		strength:
		{
			type: Number,
			default: 0
		},
		agility:
		{
			type: Number,
			default: 0
		},
		intellect:
		{
			type: Number,
			default: 0
		},
		stamina:
		{
			type: Number,
			default: 0
		},
		health:
		{
			type: Number,
			default: 0
		},
		dexterity:
		{
			type: Number,
			default: 0
		},
		type:
		{
			type: String,
			required: "Type of reward is required"
		},
		discountType:
		{
			type: String,
			default: "Percent"
		},
		discount:
		{
			type: Number,
			default: 0
		}
	},
	{
		collection: 'Reward',
	}
);

var userRewards = new Schema(
	{
		user: [user],
		reward: [rewards],
		redeemedDate:
		{
			type: Date,
			default: null
		},
		purchaseDate:
		{
			type: Date,
			default: Date.now
		}
	},
	{
		collection: 'User Rewards',
	}
);


var keyChain = new Schema(
	{
		user: 
		{
			type: [user]
		},
		key:
		{
			type: String
		},
		iv:
		{
			type: String
		}
	}
);

var guilds = new Schema(
	{
		name:
		{
			type: String
		},
		establishDate:
		{
			type: Date,
			default: Date.now
		},
		rank:
		{
			type: Number,
			default: 0
		},
		members:
		{
			type: Number,
			default: 0
		}
	},
	{
		collection: 'Guilds',
	}
);

var guildMember = new Schema(
	{
		member: [user],
		guild: [guilds],
		dateRequested:
		{
			type: Date,
			default: Date.now
		},
		dateAdmitted:
		{
			type: Date
		},
		leader:
		{
			type: Boolean,
			default: false
		}
	},
	{
		collection: 'Guild Members',
	}
);

var avatar = new Schema(
	{
		name:
		{
			type: String,
			required: "A name is required"
		},
		hairStyle:
		{
			type: Number,
			required: "A feature is required"
		},
		hairColor:
		{
			type: Number,
			required: "A feature is required"
		},
		eyeColor:
		{
			type: Number,
			required: "A feature is required"
		},
		furColor:
		{
			type: Number,
			required: "A feature is required"
		},
		animal:
		{
			type: Number,
			required: "A feature is required"
		},
		strength:
		{
			type: Number,
			default: 1
		},
		agility:
		{
			type: Number,
			default: 1
		},
		intellect:
		{
			type: Number,
			default: 1
		},
		dexterity:
		{
			type: Number,
			default: 1
		},
		stamina:
		{
			type: Number,
			default: 1
		},
		health:
		{
			type: Number,
			default: 10
		},
		level:
		{
			type: Number,
			default: 0
		}
	}
);


var userAvatar = new Schema(
	{
		avatarID: [avatar],
		userID: [user],
		createDate:
		{
			type: Date,
			default: Date.now
		},
		main:
		{
			type: Boolean,
			default: false
		}
	}
)

var battle = new Schema(
	{
		room:
		{
			type: Number,
			required: 'A Room number is required'
		},
		date:
		{
			type: Date,
			required: 'A date is required'
		}
	},
	{
		collection: "Battles",
	}
)

var equip = new Schema(
	{
		avatarID: [avatar],
		item: [userRewards]
	},
	{
		collecion: "Equiptment",
	}
)

var userBattle = new Schema(
	{
		room: [battle],
		participant: [avatar],
		result:
		{
			type: Boolean,
			default: false
		},
		damage:
		{
			type: Number
		},
		Group:
		{
			type: Number
		}
	},
	{
		collection: "User Battle",
	}
)

var message = new Schema(
	{
		to: [user],
		from: [user],
		subject:
		{
			type: String,
			required: 'A subject is required'
		},
		bodyMsg:
		{
			type: String,
			required: 'A Message is required'
		},
		date:
		{
			type: Date,
			default: Date.now
		},
		replied:
		{
			type: Boolean,
			default: false
		},
		read:
		{
			type: Boolean,
			default: false
		}
	},
	{
		collection: "Messages",
	}
)

module.exports = mongoose.model('User', user);
module.exports = mongoose.model('Keychain', keyChain);
module.exports = mongoose.model('Reward', rewards);
module.exports = mongoose.model('User Rewards', userRewards);
module.exports = mongoose.model('Guild Members', guildMember);
module.exports = mongoose.model('Guilds', guilds);
module.exports = mongoose.model('Avatar', avatar);
module.exports = mongoose.model('User Avatars', userAvatar);
module.exports = mongoose.model('Battles', battle);
module.exports = mongoose.model('User Battles', userBattle);
module.exports = mongoose.model('Equip', equip);
module.exports = mongoose.model('Messages', message);
